package acme.features.manager.workplan;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.entities.tasks.TaskVisibility;
import acme.entities.workplan.Workplan;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerWorkplanUpdateService implements AbstractUpdateService<Manager, Workplan>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerWorkplanRepository repository;

	// AbstractUpdateService<Manager, Workplan> interface ---------------------------



	@Override
	public boolean authorise(final Request<Workplan> request) {
		assert request != null;

		boolean result;
		final int workplanId;
		Workplan workplan;
		Manager manager;
		Principal principal;

		workplanId = request.getModel().getInteger("id");
		workplan = this.repository.findOneWorkplanById(workplanId);
		manager = workplan.getManager();
		principal = request.getPrincipal();
		result = workplan.getIsPublished() || !workplan.getIsPublished() && manager.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<Workplan> request, final Workplan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
		
	}

	@Override
	public void unbind(final Request<Workplan> request, final Workplan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		Collection<Task> tasks;

		if (entity.getIsPublic()) {
			tasks = this.repository.findAllTaskByManagerId(entity.getManager().getId());
		} else {
			tasks = this.repository.findAllTaskPrivateByManagerId(entity.getManager().getId());
		}
		tasks.removeAll(entity.getTasks());

		model.setAttribute("unnasignedTask", tasks);

		request.unbind(entity, model, "title", "isPublic","init","end","tasks");
		
		
	}

	@Override
	public Workplan findOne(final Request<Workplan> request) {
		assert request != null;

		Workplan result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneWorkplanById(id);

		return result;
	}

	@Override
	public void validate(final Request<Workplan> request, final Workplan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
			if(!errors.hasErrors("init")){
				final Date now= new Date();
				final boolean res=entity.getInit().after(now);
				errors.state(request, res, "init", "manager.workplan.form.error.init");
			}
			if(!errors.hasErrors("workload")) {
				final long end=entity.getEnd().getTime();
				final long init=entity.getInit().getTime();
				
				final long diff =end-init;
				final double horas=(Math.abs(diff)*1.0)/3600000;
				boolean res;
				res = horas<entity.getWorkload();
				
				errors.state(request, !res, "workload", "manager.task.form.error.workload");
			}
			
			if(!errors.hasErrors("isPublished")) {
				errors.state(request, !entity.getIsPublished(), "isPublished", "manager.workplan.form.error.isPublished");
			}
			
			if(!errors.hasErrors("end")){
				boolean res=false;
				res=entity.getEnd().after(entity.getInit());
				errors.state(request,res, "end", "manager.task.form.error.period");
			}
			
			if(!errors.hasErrors("end")) {
				boolean res=false;
				for(final Task t:entity.getTasks()) {
					if(entity.getEnd().before(t.getEndMoment())) {
						res=true;
					}
				}
				errors.state(request, !res, "end", "manager.workplan.form.error.end3");
			}
			
			if(!errors.hasErrors("init")) {
				boolean res=false;
				for(final Task t:entity.getTasks()) {
					if(entity.getInit().after(t.getInitialMoment())) {
						res=true;
					}
				}
				errors.state(request, !res, "init", "manager.workplan.form.error.init3");
			}
			
			if(!errors.hasErrors("isPublic")) {
				boolean res=false;
				for(final Task t:entity.getTasks()) {
					if(t.getVisibility()==TaskVisibility.PUBLIC && !(entity.getIsPublic())) {
						res=true;
					}
				}
				errors.state(request, !res, "isPublic", "manager.workplan.form.error.public2");
			}
			
		
	}
	@Override
	public void update(final Request<Workplan> request, final Workplan entity) {
		assert request != null;
		assert entity != null;

		final long end=entity.getEnd().getTime();
		final long init=entity.getInit().getTime();
		
		final long diff =end-init;
		final double horas=(Math.abs(diff)*1.0)/3600000;
		
		entity.setExecutionPeriod(horas);
		Double workload = 0.0;
		for(final Task t:entity.getTasks()) {
			workload=workload+t.getWorkload();
		}
		
		entity.setWorkload(workload);
		this.repository.save(entity);
		
	}

}
