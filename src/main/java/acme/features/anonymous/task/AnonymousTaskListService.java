package acme.features.anonymous.task;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousTaskListService implements AbstractListService<Anonymous, Task>{

	
	// Internal state ---------------------------------------------------------

	@Autowired

	protected AnonymousTaskRepository repository;
	
	public Double getWorkload(final Request<Task> request,  final Task entity, final Model model){
		return (double) (entity.endMoment.getTime() - entity.initialMoment.getTime()) / 3600000;
	}
		
	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "initialMoment","endMoment", "description");
	}

	@Override
	public Collection<Task> findMany(final Request<Task> request) {
		assert request != null;
		Collection<Task> result;
		result = this.repository.findMany();
		return result;
	}

}
