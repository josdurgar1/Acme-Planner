package acme.features.manager.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.features.administrator.spam.AdministratorSpamListService;
import acme.features.authenticated.task.AuthenticatedTaskRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;
import acme.spam.SpamRead;

public class ManagerTaskUpdateService implements AbstractUpdateService<Manager, Task> {

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
	final Task task;
	final Manager manager;
	final Principal principal;
	
	taskId = request.getModel().getInteger("id");
	task = this.repository.findOneTaskById(taskId);
	manager = task.getManager();
	principal = request.getPrincipal();
	
	result = !task.isFinished() && manager.getUserAccount().getId() == principal.getAccountId();
	return result;
}

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
public Task findOne(final Request<Task> request) {
	assert request != null;
	
	Task result;
	int taskId;
	
	taskId = request.getModel().getInteger("taskId");
	result = this.repository.findOneTaskById(taskId);
	
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
public void update(final Request<Task> request, final Task entity) {
	assert request != null;
	assert entity != null;

	this.repository.save(entity);
}

}