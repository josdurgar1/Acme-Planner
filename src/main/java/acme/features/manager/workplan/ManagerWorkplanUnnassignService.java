
package acme.features.manager.workplan;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.entities.workplan.Workplan;
import acme.features.authenticated.task.AuthenticatedTaskRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerWorkplanUnnassignService implements AbstractUpdateService<Manager, Workplan> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerWorkplanRepository		repository;

	// Other Services--------------------
	@Autowired
	protected AuthenticatedTaskRepository	taskRepository;


	@Override
	public boolean authorise(final Request<Workplan> request) {
		boolean result;
		int taskId;
		final Task task;
		final Manager manager;
		Principal principal;
		int workplanId;
		Workplan workplan;
		
		workplanId = request.getModel().getInteger("wId");
		workplan = this.repository.findOneWorkplanById(workplanId);
		taskId = request.getModel().getInteger("tId");
		task = this.repository.findOneTaskById(taskId);
		manager = task.getManager();
		principal = request.getPrincipal();
		result = manager.getUserAccount().getId() == principal.getAccountId() && !workplan.getIsPublished();

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
		request.unbind(entity, model, "title", "isPublic", "init", "end", "workload", "executionPeriod", "tasks");

	}

	@Override
	public Workplan findOne(final Request<Workplan> request) {
		assert request != null;

		Workplan result;
		int id;

		id = request.getModel().getInteger("wId");
		result = this.repository.findOneWorkplanById(id);

		return result;
	}

	@Override
	public void validate(final Request<Workplan> request, final Workplan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if(!errors.hasErrors("isPublished")) {
			errors.state(request, !entity.getIsPublished(), "isPublished", "manager.workplan.form.error.isPublished");
		}
	}

	@Override
	public void update(final Request<Workplan> request, final Workplan entity) {
		assert request != null;
		assert entity != null;
		Collection<Task> t;
		int id;

		id = request.getModel().getInteger("tId");
		final Task task = this.repository.findOneTaskById(id);
		t = entity.getTasks();
		t.remove(task);
		entity.setTasks((List<Task>) t);
		entity.setWorkload(entity.getWorkload()-task.getWorkload());
		this.repository.save(entity);

	}

}
