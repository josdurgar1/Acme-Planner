package acme.features.anonymous.workplan;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.entities.workplan.Workplan;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousWorkplanListService implements AbstractListService<Anonymous, Workplan>{
	// Internal state ---------------------------------------------------------

		@Autowired

		protected AnonymousWorkplanRepository repository;
		
		public Double getWorkload(final Request<Task> request,  final Task entity, final Model model){
			return (double) (entity.getEndMoment().getTime() - entity.getInitialMoment().getTime()) / 3600000;
		}
			
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
			request.unbind(entity, model, "title", "isPublic", "isPublished", "init", "end", "workload");
		}

		@Override
		public Collection<Workplan> findMany(final Request<Workplan> request) {
			assert request != null;
			Collection<Workplan> result;
			result = this.repository.findMany();
			return result;
		}
}
