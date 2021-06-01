package acme.features.manager.workplan;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.entities.tasks.TaskVisibility;
import acme.entities.workplan.Workplan;
import acme.features.administrator.spam.AdministratorSpamWordListService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;
import acme.spam.SpamRead;

@Service
public class ManagerWorkplanUpdateService implements AbstractUpdateService<Manager, Workplan> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerWorkplanRepository			repository;

	// AbstractUpdateService<Manager, Workplan> interface ---------------------------

	@Autowired
	protected AdministratorSpamWordListService	spamService;


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
		request.unbind(entity, model, "title", "isPublic", "init", "end");

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

		List<String> spamList;
		double umbral;

		spamList = this.spamService.findAllSpamWord();
		umbral = this.spamService.umbral();

		if (!errors.hasErrors("title")) {
			boolean res;

			res = SpamRead.isSpam(umbral, entity.getTitle(), spamList);
			errors.state(request, !res, "title", "manager.task.form.error.title");
		}
		final List<Task> formulario = entity.getTasks();
		final List<Task> newTasks = this.repository.findTaskByWorkplan(entity.getId());
		
		if ( !formulario.getClass().getTypeName().contains("PersistentBag")) {
			for (int i = 0; i < formulario.size(); i++) {
				final Object ob = formulario.get(i);
				final String id = ob.toString();
				final Task taskA = this.repository.findOneTaskById(Integer.parseInt(id));
				
				newTasks.add(taskA);
			}
			entity.setTasks(newTasks);
		} else {
			entity.setTasks(newTasks);
		}

		if (!errors.hasErrors("init")) {
			final Date now = new Date();
			final boolean res = entity.getInit().after(now);
			errors.state(request, res, "init", "manager.workplan.form.error.init");
		}
		if (!errors.hasErrors("workload")&&(entity.getEnd()!=null)&&(entity.getInit()!=null)) {
			final long end = entity.getEnd().getTime();
			final long init = entity.getInit().getTime();

			final long diff = end - init;
			final double horas = (Math.abs(diff) * 1.0) / 3600000;
			boolean res;
			res = horas < entity.getWorkload();

			errors.state(request, !res, "workload", "manager.task.form.error.workload");
		}

		if (!errors.hasErrors("isPublished")) {
			errors.state(request, !entity.getIsPublished(), "isPublished", "manager.workplan.form.error.isPublished");
		}

		if (!errors.hasErrors("end")&&(entity.getEnd()!=null)&&(entity.getInit()!=null)) {
			boolean res = false;
			res = entity.getEnd().after(entity.getInit());
			errors.state(request, res, "end", "manager.task.form.error.period");
		}

		if (!errors.hasErrors("end")) {
			boolean res = false;
			for (final Task t : entity.getTasks()) {
				if (entity.getEnd().before(t.getEndMoment())) {
					res = true;
				}
			}
			errors.state(request, !res, "end", "manager.workplan.form.error.end3");
		}

		if (!errors.hasErrors("init")) {
			boolean res = false;
			for (final Task t : entity.getTasks()) {
				if (entity.getInit().after(t.getInitialMoment())) {
					res = true;
				}
			}
			errors.state(request, !res, "init", "manager.workplan.form.error.init3");
		}
		final boolean res2=entity.getIsPublic();
		if (!errors.hasErrors("isPublic")) {
			boolean res = false;
			for (final Task t : entity.getTasks()) {
				if (t.getVisibility() == TaskVisibility.PRIVATE && (res2)) {
					res = true;
				}
			}
			errors.state(request, !res, "isPublic", "manager.workplan.form.error.public2");
		}

	}
	@Override
	public void update(final Request<Workplan> request, final Workplan entity) {
		assert request != null;
		assert entity != null;

		final long end = entity.getEnd().getTime();
		final long init = entity.getInit().getTime();

		final long diff = end - init;
		final double horas = (Math.abs(diff) * 1.0) / 3600000;

		entity.setExecutionPeriod(horas);
		Double workload = 0.0;
		for (final Task t : entity.getTasks()) {
			workload = workload + t.getWorkload();
		}

		entity.setWorkload(workload);
		this.repository.save(entity);

	}

}