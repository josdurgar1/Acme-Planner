package acme.features.manager.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.entities.workplan.Workplan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerTaskRepository extends AbstractRepository{
	
	@Query("select t from Task t where t.endMoment < current_timestamp() and t.visibility = 0 order by t.initialMoment DESC")
	Collection<Task> findMany();
	
	@Query("select t from Task t where t.id = ?1")
	Task findOneTaskById(int id);
	
	@Query("select DATEDIFF(t.endMoment, t.initialMoment) from Task t where t.id LIKE ?1")
	Double getExecutionPeriod(int id);
	
	@Query("select t from Task t where t.manager.id = ?1")
	Collection<Task> findManyTasksByManagerId(int id);
	
	@Query("select m from Manager m where m.userAccount.id = ?1")
	Manager findOneManagerByUserAccountId(int id);
		
	@Query("select w from Workplan w JOIN w.tasks t where t.id=?1")
	Collection<Workplan> findWorkplanByTask(int id);
}
