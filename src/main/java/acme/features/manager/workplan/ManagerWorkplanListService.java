package acme.features.manager.workplan;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.workplan.Workplan;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class ManagerWorkplanListService implements AbstractListService<Authenticated, Workplan>{

	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerWorkplanRepository repository;
	// AbstractListService<Manager, Workplan> interface ---------------------------
		
	@Override
	public boolean authorise(final Request<Workplan> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Workplan> request, final Workplan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "isPublic", "init","end","workload");
		
	}

	@Override
	public Collection<Workplan> findMany(final Request<Workplan> request) {
		Collection<Workplan> res;
		final int id=request.getPrincipal().getAccountId();
		res=this.repository.findManyByManagerId(id);
		
		return res;
	}

	
}
