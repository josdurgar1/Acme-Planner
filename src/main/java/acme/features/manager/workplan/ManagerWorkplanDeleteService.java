package acme.features.manager.workplan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.workplan.Workplan;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class ManagerWorkplanDeleteService implements AbstractDeleteService<Manager, Workplan>{

	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected ManagerWorkplanRepository repository;

		// AbstractDeleteService<Manager, Workplan> interface -------------------------
	@Override
	public boolean authorise(final Request<Workplan> request) {
		assert request != null;

		boolean result;
		int workplanId;
		Workplan workplan;
		Manager manager;
		Principal principal;

		workplanId = request.getModel().getInteger("id");
		workplan = this.repository.findOneWorkplanById(workplanId);
		manager = workplan.getManager();
		principal = request.getPrincipal();
		result = !workplan.getIsPublished() && manager.getUserAccount().getId() == principal.getAccountId();

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
		request.unbind(entity, model, "title", "isPublic", "init","end","workload","isPublished","executionPeriod");
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
		
	}

	@Override
	public void delete(final Request<Workplan> request, final Workplan entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
		
	}

}
