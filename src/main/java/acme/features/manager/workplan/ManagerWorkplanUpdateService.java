package acme.features.manager.workplan;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.workplan.Workplan;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerWorkplanUpdateService implements AbstractUpdateService<Authenticated, Workplan>{

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

		request.unbind(entity, model, "isPublic","init","end","workload");
		
		
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
				final Date periodStart = entity.getInit();
				final Date periodEnd = entity.getEnd();
				final double workload = entity.getWorkload();
				
				final double hoursW = Math.floor(workload);
				final double minsW = (workload-hoursW)*100;
				boolean res = false;
				final long milliseconds = Math.abs(periodEnd.getTime() - periodStart.getTime());
				final long diff = TimeUnit.MINUTES.convert(milliseconds, TimeUnit.MILLISECONDS);
				final double hours = Math.floor(diff/60.0);
				final double mins = diff%60;
				res =  (hoursW > hours) || (hoursW == hours && minsW > mins);
				
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

		this.repository.save(entity);
		
	}

}
