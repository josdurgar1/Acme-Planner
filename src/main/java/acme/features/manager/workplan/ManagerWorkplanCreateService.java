
package acme.features.manager.workplan;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.workplan.Workplan;
import acme.features.administrator.spam.AdministratorSpamWordListService;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;
import acme.spam.SpamRead;

@Service
public class ManagerWorkplanCreateService implements AbstractCreateService<Manager, Workplan> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerWorkplanRepository			repository;

	// AbstractCreateService<Authenticated, Consumer> ---------------------------
	@Autowired
	protected AdministratorSpamWordListService	spamService;


	@Override
	public boolean authorise(final Request<Workplan> request) {
		assert request != null;

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

		request.unbind(entity, model, "title", "isPublic", "init", "end", "tasks");

	}

	@Override
	public void validate(final Request<Workplan> request, final Workplan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		List<String> spamList;
		double umbral;

		spamList = this.spamService.findAllSpamWord();
		umbral = this.spamService.umbral();
		//request.getModel().setAttribute("tasks", this.repository.findAllTask2ByManagerId(request.getPrincipal().getActiveRoleId()));
		if (!errors.hasErrors("init")) {
			final Date now = new Date();
			final boolean res = entity.getInit().after(now);
			errors.state(request, res, "init", "manager.workplan.form.error.init");
		}
		if (!errors.hasErrors("workload") && !errors.hasErrors("init") && !errors.hasErrors("end")) {
			final long end = entity.getEnd().getTime();
			final long init = entity.getInit().getTime();

			final long diff = end - init;
			final double horas = (Math.abs(diff) * 1.0) / 3600000;
			boolean res;
			res = horas < entity.getWorkload();

			errors.state(request, !res, "workload", "manager.task.form.error.workload");
		}

		if (!errors.hasErrors("end")) {
			boolean res = false;
			res = entity.getEnd().after(entity.getInit());
			errors.state(request, res, "end", "manager.task.form.error.period");
		}
		if (!errors.hasErrors("title")) {
			boolean res;

			res = SpamRead.isSpam(umbral, entity.getTitle(), spamList);
			errors.state(request, !res, "title", "manager.task.form.error.title");
		}

	}

	@Override
	public Workplan instantiate(final Request<Workplan> request) {
		assert request != null;

		Workplan result;
		Manager manager;

		manager = this.repository.findOneManagerById(request.getPrincipal().getActiveRoleId());

		result = new Workplan();
		result.setManager(manager);
		result.setWorkload(0.0);
		//final Collection<Task> tasks = this.repository.findAllTask2ByManagerId(request.getPrincipal().getActiveRoleId());
		//result.setTasks(tasks);
		result.setIsPublished(false);
		return result;
	}

	@Override
	public void create(final Request<Workplan> request, final Workplan entity) {
		assert request != null;
		assert entity != null;

		final long end = entity.getEnd().getTime();
		final long init = entity.getInit().getTime();

		final long diff = end - init;
		final double horas = (Math.abs(diff) * 1.0) / 3600000;

		entity.setExecutionPeriod(horas);
		entity.setWorkload(0.0);
		entity.setIsPublished(false);
		this.repository.save(entity);

	}

	@Override
	public void onSuccess(final Request<Workplan> request, final Response<Workplan> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
