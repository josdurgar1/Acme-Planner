package acme.features.anonymous.workplan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.workplan.Workplan;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractShowService;

@Service
public class AnonymousWorkplanShowService implements AbstractShowService<Anonymous, Workplan> {
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AnonymousWorkplanRepository repository;


		@Override
		public boolean authorise(final Request<Workplan> request) {
			assert request != null;

			return true;
		}

		// AbstractShowService<Anonymous, Task> interface --------------------------

		@Override
		public void unbind(final Request<Workplan> request, final Workplan entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "title", "isPublic", "isPublished","executionPeriod", "workload", "init", "end", "tasks", "manager.name");
		}

		@Override
		public Workplan findOne(final Request<Workplan> request) {
			Workplan result;
			int id;
			id = request.getModel().getInteger("id");
			result = this.repository.findOneWorkplanById(id);
			return result;
		}
}

