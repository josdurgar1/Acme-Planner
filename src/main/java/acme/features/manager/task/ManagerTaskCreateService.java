package acme.features.manager.task;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.features.administrator.spam.AdministratorSpamListService;
import acme.features.authenticated.task.AuthenticatedTaskRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
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
	

	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;
		
		boolean result;
		final int taskId;
		Task task;
		final Manager manager;
		final Principal principal;
		
		taskId = request.getModel().getInteger("taskId");
		task = this.repository.findOneTaskById(taskId);
		manager = task.getManager();
		principal = request.getPrincipal();
		
		result = manager.getId() == principal.getAccountId();
		
		return result;
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
		
		request.unbind(entity, model, "title", "initialMoment","endMoment", "executionPeriod", "workload", "description");
	}

	@Override
	public Task instantiate(final Request<Task> request) {
		assert request != null;
		
		Task result;
		Date initialMoment;
		Date endMoment;
		
		
		initialMoment = new Date(System.currentTimeMillis() -1);
		endMoment = new Date(System.currentTimeMillis() + 1000);
		
		
		result = new Task();
		result.setTitle("Task-01");
		result.setInitialMoment(initialMoment);
		result.setEndMoment(endMoment);
		result.setWorkload(5.0);
		result.setDescription("This is a description");
		result.setLink("http://example.org");
		result.getExecutionPeriod();
		result.setIsPublic(true);
		
		
		return result;
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		
		List<String> spamList;
		double umbral;
		
		spamList = this.spamService.findAllSpamWord();
		umbral = this.spamService.umbral();
		
		if (SpamRead.isSpam(umbral, entity.getTitle(), spamList) || SpamRead.isSpam(umbral, entity.getDescription(), spamList)){
			
			switch (request.getLocale().getLanguage()) {
				case "es":  errors.add("text", "Este mensaje se considera SPAM. El umbral es del "+umbral+"%");
                     break;
				case "en":  errors.add("text", "This message is considered SPAM. The threshold is "+umbral+"%");
                     break;                
				default: errors.add("text", "SPAM");
            		break;
			}
		}
		assert errors != null;
		
	}

	@Override
	public void create(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;
		
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setInitialMoment(moment);
		
		this.repository.save(entity);
	}
	
	
	
	
	
	
}
