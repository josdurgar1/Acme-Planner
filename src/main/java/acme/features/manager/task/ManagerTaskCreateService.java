package acme.features.manager.task;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.entities.tasks.TaskVisibility;
import acme.features.administrator.spam.AdministratorSpamListService;
import acme.features.authenticated.manager.AuthenticatedManagerRepository;
import acme.features.authenticated.task.AuthenticatedTaskRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;
import acme.spam.SpamRead;

@Service
public class ManagerTaskCreateService implements AbstractCreateService<Manager, Task> {
	// Internal state ----------------------------------------------------
	
	@Autowired
	protected AuthenticatedTaskRepository repository;
	
	// Other Services----------------
	
	@Autowired
	protected AdministratorSpamListService spamService;
	
	@Autowired
	protected AuthenticatedManagerRepository managerService;
	

	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;	
		return true;
	}

	// AbstractCreateService<Manager, Task> interface --------------------------

	
	@Override
	public void bind(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors);
	}
	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "initialMoment","endMoment", "workload", "description","visibility", "Finished", "executionPeriod");
	}

	@Override
	public Task instantiate(final Request<Task> request) {
		assert request != null;
		
		Task result;
		final Manager manager;
		Date initialMoment;
		Date endMoment;
		Calendar initialCalendar;
		Calendar endCalendar;

		
		/*initialMoment = new Date(System.currentTimeMillis() -1);
		endMoment = new Date(System.currentTimeMillis() + 10000000);*/
		manager = this.managerService.findOneManagerByUserAccountId(request.getPrincipal().getAccountId());
		
		initialMoment = new Date();
		initialCalendar = Calendar.getInstance();
		initialCalendar.setTime(initialMoment);
		initialCalendar.add(Calendar.SECOND, 0);
		initialMoment = initialCalendar.getTime();
		
		endMoment = new Date();
		endCalendar = Calendar.getInstance();
		endCalendar.setTime(endMoment);
		endCalendar.add(Calendar.SECOND, 3600);
		endMoment = endCalendar.getTime();
		
		
		
		result = new Task();
		result.setTitle("Task-01");
		result.setInitialMoment(initialMoment);
		result.setEndMoment(endMoment);
		result.setDescription("This is a description");
		result.setLink("This is a link");
		result.setWorkload(0.0);
		result.setVisibility(TaskVisibility.PUBLIC);
		result.setManager(manager);
		request.getModel().setAttribute("isFinished", false);
				
		return result;
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		List<String> spamList;
		double umbral;
		
		spamList = this.spamService.findAllSpamWord();
		umbral = this.spamService.umbral();
				
				if(!errors.hasErrors("initialMoment")) {
					final Date now = new Date();
					final boolean res = entity.getInitialMoment().after(now);
					errors.state(request, res, "initialMoment", "manager.task.form.error.initialMoment");
				}
		
				if(!errors.hasErrors("endMoment")) {
					final boolean res = entity.getInitialMoment().after(entity.getEndMoment());
					errors.state(request, !res, "endMoment", "manager.task.form.error.endMoment");

				}
				if(!errors.hasErrors("workload")) {
					final long endMoment = entity.getEndMoment().getTime();
					final long initialMoment = entity.getInitialMoment().getTime();
					
					final long diff = endMoment-initialMoment;
					final double horas = (Math.abs(diff)*1.0)/3600000;
					boolean res;
					res = horas<entity.getWorkload();
					
					errors.state(request, !res, "workload", "manager.task.form.error.workload");
					
				}
				if(!errors.hasErrors("description")) {
					boolean res;
					
					res = SpamRead.isSpam(umbral, entity.getDescription(), spamList);
					errors.state(request, !res, "description", "manager.task.form.error.description");
				}
		
	}

	@Override
	public void create(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;
		
		final long endMoment = entity.getEndMoment().getTime();
		final long initialMoment = entity.getInitialMoment().getTime();
		
		final long diff = endMoment-initialMoment;
		final double horas = (Math.abs(diff)*1.0)/3600000;
		
		final Date currentMoment = new Date(System.currentTimeMillis()-1);
		final boolean isFinished = currentMoment.after(entity.getEndMoment());
		
		request.getModel().setAttribute("isFinished", isFinished);;
		entity.setExecutionPeriod(horas);
		
		this.repository.save(entity);
	}
	
	
	
	
	
	
}
