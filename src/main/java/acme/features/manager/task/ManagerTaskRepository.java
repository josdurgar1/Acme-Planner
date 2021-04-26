package acme.features.manager.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerTaskRepository extends AbstractRepository{
	
	@Query("select t from Task t where t.endMoment < current_timestamp() order by t.initialMoment DESC")
	Collection<Task> findMany();
	
	@Query("select t from Task t where t.id = ?1 and t.endMoment < current_timestamp()")
	Task findOneTaskById(int id);
	
	@Query("select DATEDIFF(t.endMoment, t.initialMoment) from Task t where t.id LIKE ?1")
	Double getExecutionPeriod(int id);
		
}
