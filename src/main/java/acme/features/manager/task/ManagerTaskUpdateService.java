
package acme.features.manager.task;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.features.administrator.spam.AdministratorSpamWordListService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;
import acme.spam.SpamRead;

@Service
public class ManagerTaskUpdateService implements AbstractUpdateService<Manager, Task> {

	@Autowired
	protected ManagerTaskRepository				repository;

	// Other Services----------------

	@Autowired
	protected AdministratorSpamWordListService	spamService;


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

		request.unbind(entity, model, "title", "initialMoment", "endMoment", "workload", "description", "visibility", "executionPeriod");
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

		List<String> spamList;
		double umbral;

		spamList = this.spamService.findAllSpamWord();
		umbral = this.spamService.umbral();

		if (!errors.hasErrors("initialMoment")) {

			boolean res;
			res = entity.getInitialMoment() == null;

			errors.state(request, !res, "initialMoment", "manager.task.form.error.nullinitialMoment");

		}
		if (!errors.hasErrors("endMoment")) {

			boolean res;
			res = entity.getEndMoment() == null;

			errors.state(request, !res, "endMoment", "manager.task.form.error.nullendMoment");

		}
		if (!errors.hasErrors("workload")) {

			boolean res;
			res = entity.getWorkload() == null;

			errors.state(request, !res, "workload", "manager.task.form.error.nullworkload");

		}

		if (!errors.hasErrors("endMoment")) {
			final boolean res = entity.getInitialMoment().after(entity.getEndMoment());
			errors.state(request, !res, "endMoment", "manager.task.form.error.endMoment");

		}
		if (!errors.hasErrors("initialMoment")) {
			final Date now = new Date();
			final boolean res = entity.getInitialMoment().after(now);
			errors.state(request, res, "initialMoment", "manager.task.form.error.initialMoment");
		}

		if (!errors.hasErrors("workload")) {
			final long endMoment = entity.getEndMoment().getTime();
			final long initialMoment = entity.getInitialMoment().getTime();

			final long diff = endMoment - initialMoment;
			final double horas = (Math.abs(diff) * 1.0) / 3600000;
			boolean res;
			res = horas < entity.getWorkload();

			errors.state(request, !res, "workload", "manager.task.form.error.workload");

		}
		if (!errors.hasErrors("workload")) {
			final double workload = entity.getWorkload();
			final boolean res;

			res = workload > 0.0;

			errors.state(request, res, "workload", "manager.task.form.error.negativeworkload");

		}

		if (!errors.hasErrors("description")) {
			boolean res;

			res = SpamRead.isSpam(umbral, entity.getDescription(), spamList);
			errors.state(request, !res, "description", "manager.task.form.error.description");
		}

		if (!errors.hasErrors("title")) {
			boolean res;

			res = SpamRead.isSpam(umbral, entity.getTitle(), spamList);
			errors.state(request, !res, "title", "manager.task.form.error.title");
		}
		if (!errors.hasErrors("endMoment")&& entity.getEndMoment()!=null) {
			boolean res;
			final Date now = new Date();
			res=entity.getEndMoment().before(now);
			errors.state(request, !res, "endMoment", "manager.task.form.error.ended");
		}

	}

	@Override
	public void update(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;

		final long endMoment = entity.getEndMoment().getTime();
		final long initialMoment = entity.getInitialMoment().getTime();

		final long diff = endMoment - initialMoment;
		final double horas = (Math.abs(diff) * 1.0) / 3600000;

		entity.setExecutionPeriod(horas);

		this.repository.save(entity);
	}

}
