package acme.features.manager.workplan;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.entities.workplan.Workplan;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class ManagerWorkplanShowService implements AbstractShowService<Manager, Workplan>{

	// Internal state ---------------------------------------------------------

		@Autowired
		protected ManagerWorkplanRepository repository;
		
		// AbstractShowService<Manager, Workplan> interface ---------------------------


	
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
	public void unbind(final Request<Workplan> request, final Workplan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		Collection<Task>tasks;
		
		if(entity.getIsPublic()) {
		tasks=this.repository.findAllTaskByManagerId(entity.getManager().getId(), entity.getInit(), entity.getEnd());
		}else {
			tasks=this.repository.findAllTaskPrivateByManagerId(entity.getManager().getId(), entity.getInit(), entity.getEnd());
		}
		//tasks.removeAll(entity.getTasks());
		final Date suggestionInit=this.repository.findMinInitWorkplanTask(entity.getId());
		if(suggestionInit!=null) {
			suggestionInit.setHours(8);
			suggestionInit.setMinutes(0);
			suggestionInit.setDate(suggestionInit.getDate()-1);
		}
		final Date suggestionEnd=this.repository.findMaxEndWorkplanTask(entity.getId());
		if(suggestionEnd!=null) {
			suggestionEnd.setHours(17);
			suggestionEnd.setMinutes(0);
			suggestionEnd.setDate(suggestionEnd.getDate()+1);
		}
		model.setAttribute("suggestionInit", suggestionInit);
		model.setAttribute("suggestionEnd", suggestionEnd);
		model.setAttribute("allTask", tasks);
		request.unbind(entity, model, "title", "isPublic", "init","end","workload","isPublished","executionPeriod","tasks");
		
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
	

	

}
