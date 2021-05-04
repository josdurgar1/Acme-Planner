package acme.features.authenticated.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.tasks.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedTaskRepository extends AbstractRepository{
	
	@Query("select t from Task t where t.endMoment < current_timestamp() and t.visibility=0 order by t.initialMoment DESC")
	Collection<Task> findMany();
	
	@Query("select t from Task t where t.id = ?1 and t.visibility=0 and  t.endMoment < current_timestamp()")
	Task findOneTaskById(int id);
	
	@Query("select DATEDIFF(t.endMoment, t.initialMoment) from Task t where t.id LIKE ?1")
	Double getExecutionPeriod(int id);
}
