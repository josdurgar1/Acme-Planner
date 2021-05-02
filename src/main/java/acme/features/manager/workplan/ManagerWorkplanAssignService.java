
package acme.features.manager.workplan;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.entities.tasks.TaskVisibility;
import acme.entities.workplan.Workplan;
import acme.features.authenticated.task.AuthenticatedTaskRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerWorkplanAssignService implements AbstractUpdateService<Manager, Workplan> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerWorkplanRepository		repository;

	// Other Services--------------------
	@Autowired
	protected AuthenticatedTaskRepository	taskRepository;


	@Override
	public boolean authorise(final Request<Workplan> request) {
		assert request != null;

		//			boolean result;
		//			int taskId;
		//			final Task task;
		//			final Manager manager;
		//			Principal principal;
		//
		//			taskId = request.getModel().getInteger("tId");
		//			task = this.taskRepository.findOneTaskById(taskId);
		//			manager = task.getManager();
		//			principal = request.getPrincipal();
		//			result = manager.getUserAccount().getId() == principal.getAccountId();
		//
		//			return result;
		return true;
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
		request.unbind(entity, model, "title", "isPublic", "init", "end", "workload", "isPublished", "executionPeriod", "tasks");

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
		int id;

		id = request.getModel().getInteger("tId");
		final Task task = this.repository.findOneTaskById(id);
		
		if(!errors.hasErrors("isPublic")) {
		final boolean res= !(entity.getIsPublic()) && !(task.getVisibility()==TaskVisibility.PRIVATE);
			errors.state(request, !res, "isPublic", "manager.workplan.form.error.private");
		}
		
		if (!errors.hasErrors("workload")) {
			Double workloadS = 0.0;
			for (final Task t : entity.getTasks()) {
				workloadS += t.getWorkload();
			}
			
			workloadS = workloadS + task.getWorkload();
			final boolean res = workloadS > entity.getExecutionPeriod();
			errors.state(request, !res, "tasks", "manager.workplan.form.error.workload");
		}
		if(!errors.hasErrors("isPublished")) {
			errors.state(request, !entity.getIsPublished(), "isPublished", "manager.workplan.form.error.isPublished");
		}
		
		if(!errors.hasErrors("init")) {
			errors.state(request, !entity.getInit().after(task.getInitialMoment()), "init", "manager.workplan.form.error.init2");
		}
		if(!errors.hasErrors("end")) {
			errors.state(request, !entity.getEnd().before(task.getEndMoment()), "init", "manager.workplan.form.error.end2");
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
		t.add(task);
		entity.setTasks(t);
		Double w = 0.0;
		for (final Task ta : entity.getTasks()) {
			w += ta.getWorkload();
		}
		entity.setWorkload(w);
		this.repository.save(entity);

	}

}
