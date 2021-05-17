package acme.features.anonymous.task;

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

		request.unbind(entity, model, "title", "initialMoment","endMoment", "executionPeriod", "workload", "description","visibility");
	}

	@Override
	public Task findOne(final Request<Task> request) {
		assert request != null;
//		Double executionPeriod;
//		final Date date = new Date();
		Task result;
		int id;
		

		id = request.getModel().getInteger("id");
//		executionPeriod = this.repository.getExecutionPeriod(id);
		result = this.repository.findOneTaskById(id);
//		if(result.initialMoment.getTime()-date.getTime()>0) {
//			result.setWorkload(0.);
//		}else if(result.endMoment.getTime()-date.getTime()<0) {
//			result.setWorkload((double)(result.endMoment.getTime() - result.initialMoment.getTime()) / 3600000);
//		}else {
//			result.setWorkload((double) (date.getTime() - result.initialMoment.getTime()) / 3600000);
//		}
//		result.setExecutionPeriod(executionPeriod);
		
		

		return result;
	}
//	public Double getWorkload(final Task entity){
//		return (double) (entity.endMoment.getTime() - entity.initialMoment.getTime()) / 3600000;
//	}
}
