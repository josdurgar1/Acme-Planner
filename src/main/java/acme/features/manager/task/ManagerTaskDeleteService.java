package acme.features.manager.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class ManagerTaskDeleteService implements AbstractDeleteService<Manager, Task> {
	
	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected ManagerTaskRepository repository;
	

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
	
	result = manager.getUserAccount().getId() == principal.getAccountId();
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
	
	request.unbind(entity, model, "title", "initialMoment","endMoment", "workload", "description", "visibility", "executionPeriod");
}

@Override
public Task findOne(final Request<Task> request) {
	assert request != null;
	
	Task result;
	int taskId;
	
	taskId = request.getModel().getInteger("id");
	result = this.repository.findOneTaskById(taskId);
	
	return result;
}

@Override
public void validate(final Request<Task> request, final Task entity, final Errors errors) {
	assert request != null;
	assert entity != null;
	assert errors != null;
	
	
}

@Override
public void delete(final Request<Task> request, final Task entity) {
	assert request != null;
	assert entity != null;
	
	this.repository.delete(entity);
}

}
