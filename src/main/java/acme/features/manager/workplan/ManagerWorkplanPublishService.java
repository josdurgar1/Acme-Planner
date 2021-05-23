package acme.features.manager.workplan;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.entities.workplan.Workplan;
import acme.features.administrator.spam.AdministratorSpamWordListService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;
import acme.spam.SpamRead;

@Service
public class ManagerWorkplanPublishService implements AbstractUpdateService<Manager, Workplan>{


	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerWorkplanRepository repository;
	
	// Other Services--------------------
		@Autowired
		protected AdministratorSpamWordListService spamService;

	// AbstractListService<Employer, Job> interface ---------------------------
	
	@Override
	public boolean authorise(final Request<Workplan> request) {
		assert request != null;

		boolean result;
		int workplanId;
		final Workplan workplan;
		final Manager manager;
		Principal principal;

		workplanId = request.getModel().getInteger("id");
		workplan = this.repository.findOneWorkplanById(workplanId);
		manager = workplan.getManager();
		principal = request.getPrincipal();
		result =manager.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<Workplan> request, final Workplan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "isPublic","workload","executionPeriod","tasks");
		
	}

	@Override
	public void unbind(final Request<Workplan> request, final Workplan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		Collection<Task> tasks;

		final boolean aux=entity.getIsPublic();
		if (aux) {
			tasks = this.repository.findAllTaskPublicByManagerId(entity.getManager().getId(), entity.getInit(), entity.getEnd());
		} else {
			tasks = this.repository.findAllTaskByManagerId(entity.getManager().getId(), entity.getInit(), entity.getEnd());
		}
		//tasks.removeAll(entity.getTasks());

		model.setAttribute("unnasignedTask", tasks);
		request.unbind(entity, model, "title", "init","end");
		
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
		List<String> spamList;
		double umbral;
		umbral= this.spamService.umbral();
		spamList=this.spamService.findAllSpamWord();
		
		
		if (!errors.hasErrors("title")){
			final boolean res=SpamRead.isSpam(umbral, entity.getTitle(), spamList);
			
			errors.state(request, !res, "title", "manager.workplan.form.error.spam");
			}
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
		
		if(!errors.hasErrors("end")){
			boolean res=false;
			res=entity.getEnd().after(entity.getInit());
			errors.state(request,res, "end", "manager.task.form.error.period");
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
		Double workload = 0.0;
		for(final Task t:entity.getTasks()) {
			workload=workload+t.getWorkload();
		}
		
		entity.setWorkload(workload);
		entity.setExecutionPeriod(horas);
		entity.setIsPublished(true);
		this.repository.save(entity);
	}

	

}
