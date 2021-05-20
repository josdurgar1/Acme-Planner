package acme.features.manager.workplan;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
		
		final boolean aux=entity.getIsPublic();
		if (aux) {
		tasks=this.repository.findAllTaskPublicByManagerId(entity.getManager().getId(), entity.getInit(), entity.getEnd());
		}else {
			tasks=this.repository.findAllTaskByManagerId(entity.getManager().getId(), entity.getInit(), entity.getEnd());
		}
		Date suggestionInit=this.repository.findMinInitWorkplanTask(entity.getId());
		if(suggestionInit!=null) {
			final Calendar calendar = Calendar.getInstance(); // creates a new calendar instance
			calendar.setTime(suggestionInit);   // assigns calendar to given date 
			calendar.set(Calendar.HOUR_OF_DAY, 8); // gets hour in 24h format
			calendar.set(Calendar.MINUTE, 0);        // gets hour in 12h format
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)-1);
			suggestionInit=calendar.getTime();
		}
		Date suggestionEnd=this.repository.findMaxEndWorkplanTask(entity.getId());
		if(suggestionEnd!=null) {
			final Calendar calendar = Calendar.getInstance(); // creates a new calendar instance
			calendar.setTime(suggestionEnd);   // assigns calendar to given date 
			calendar.set(Calendar.HOUR_OF_DAY, 17); // gets hour in 24h format
			calendar.set(Calendar.MINUTE, 0);        // gets hour in 12h format
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+1);
			suggestionEnd=calendar.getTime();
		}
		model.setAttribute("suggestionInit", suggestionInit);
		model.setAttribute("suggestionEnd", suggestionEnd);
		final List<Task>assignedTasks=entity.getTasks();
		tasks.removeAll(assignedTasks);
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

