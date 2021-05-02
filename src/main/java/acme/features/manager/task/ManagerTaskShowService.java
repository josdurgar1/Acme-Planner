package acme.features.manager.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class ManagerTaskShowService implements AbstractShowService<Manager, Task> {

	// Internal state ---------------------------------------------------------

		@Autowired
		protected ManagerTaskRepository repository;


		@Override
		public boolean authorise(final Request<Task> request) {
			assert request != null;

			return true;
		}

		// AbstractShowService<Manager, Task> interface --------------------------

		@Override
		public void unbind(final Request<Task> request, final Task entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;
			
			final boolean isPrincipal = entity.getManager().getId() == request.getPrincipal().getActiveRoleId();
			
			model.setAttribute("checkP", isPrincipal);

			request.unbind(entity, model, "title", "initialMoment","endMoment", "workload", "description", "visibility","finished", "executionPeriod");
		}

		@Override
		public Task findOne(final Request<Task> request) {
			assert request != null;
//			final Double executionPeriod;
			Task result;
			int id;
			

			id = request.getModel().getInteger("id");
//			executionPeriod = this.repository.getExecutionPeriod(id);
			result = this.repository.findOneTaskById(id);
//			result.setWorkload((double) (result.endMoment.getTime() - result.initialMoment.getTime()) / 3600000);
//			result.setExecutionPeriod(executionPeriod);
			
			

			return result;
		}
}
