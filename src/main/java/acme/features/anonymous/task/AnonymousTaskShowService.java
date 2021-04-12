package acme.features.anonymous.task;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.tasks.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractShowService;

@Service
public class AnonymousTaskShowService implements AbstractShowService<Anonymous, Task> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnonymousTaskRepository repository;


	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;

		return true;
	}

	// AbstractShowService<Anonymous, Task> interface --------------------------

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "initialMoment","endMoment", "executionPeriod", "workload", "description");
	}

	@Override
	public Task findOne(final Request<Task> request) {
		assert request != null;
		Double executionPeriod;
		final Date date = new Date();
		Task result;
		int id;
		

		id = request.getModel().getInteger("id");
		executionPeriod = this.repository.getExecutionPeriod(id);
		result = this.repository.findOneTaskById(id);
		result.setWorkload((double) (result.initialMoment.getTime() - date.getTime()) / 3600000);
		if(result.initialMoment.getTime()-date.getTime()>0) {
			result.setWorkload(0.);
		}
		result.setExecutionPeriod(executionPeriod);
		
		

		return result;
	}
	public Double getWorkload(final Task entity){
		return (double) (entity.endMoment.getTime() - entity.initialMoment.getTime()) / 3600000;
	}
}
