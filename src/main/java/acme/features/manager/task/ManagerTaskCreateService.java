package acme.features.manager.task;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.features.administrator.spam.AdministratorSpamListService;
import acme.features.authenticated.manager.AuthenticatedManagerRepository;
import acme.features.authenticated.task.AuthenticatedTaskRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Manager;
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
		
		request.unbind(entity, model, "title", "initialMoment","endMoment", "executionPeriod", "workload", "description", "manager");
	}

	@Override
	public Task instantiate(final Request<Task> request) {
		assert request != null;
		
		Task result;
		Manager manager;
		Date initialMoment;
		Date endMoment;
		
		
		initialMoment = new Date(System.currentTimeMillis() -1);
		endMoment = new Date(System.currentTimeMillis() + 1000);
		manager = this.managerService.findOneManagerByUserAccountId(request.getPrincipal().getAccountId());
		
		
		result = new Task();
		result.setTitle("Task-01");
		result.setInitialMoment(initialMoment);
		result.setEndMoment(endMoment);
		result.setWorkload(5.0);
		result.setDescription("This is a description");
		result.setLink("http://example.org");
		result.getExecutionPeriod();
		result.setIsPublic(true);
		result.setManager(manager);
		
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
		if (entity.getEndMoment().before(entity.getInitialMoment())) {
			
			switch (request.getLocale().getLanguage()) {
			case "es": errors.add("text", "La fecha de final no puede ser anterior a la de inicio.");
				break;
			case "en": errors.add("text", "The end date can't be earlier than the start date.");
				break;
			default: errors.add("text", "DATE");
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
