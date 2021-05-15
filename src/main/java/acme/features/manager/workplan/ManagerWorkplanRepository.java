package acme.features.manager.workplan;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.entities.workplan.Workplan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerWorkplanRepository extends AbstractRepository {
	
	@Query("select m from Manager m where m.id = ?1")
	Manager findOneManagerById(int id);
	
	@Query("select w from Workplan w where w.manager.userAccount.id = ?1")
	Collection<Workplan> findManyByManagerId(int managerId);
	
	@Query("select w from Workplan w where w.manager.userAccount.id = ?1 and w.isPublished=0")
	Collection<Workplan> findAllNotPublishByManagerId(int managerId);

	@Query("select w from Workplan w where w.id=?1")
	Workplan findOneWorkplanById(int workplanId);
	
	
	
	
	
	@Query("select t from Task t where t.visibility=1 and t.manager.id=?1 and t.initialMoment>?2 and t.endMoment<?3 ")
	Collection<Task> findAllTaskPrivateByManagerId(int managerId, Date init, Date end);

	@Query("select t from Task t where t.visibility=0 and t.manager.id=?1 and t.initialMoment>?2 and t.endMoment<?3")
	Collection<Task> findAllTaskPublicByManagerId(int managerId, Date init, Date end);
	
	@Query("select t from Task t where t.manager.id=?1 and t.initialMoment>?2 and t.endMoment<?3")
	Collection<Task> findAllTaskByManagerId(int managerId, Date init, Date end);
	
	@Query("select t from Task t where t.manager.id=?1")
	Collection<Task> findAllTask2ByManagerId(int managerId);


	@Query("select t from Task t where t.id = ?1")
	Task findOneTaskById(int id);
	
	@Query("select min(t.initialMoment) from Workplan w join w.tasks t where w.id=?1")
	Date findMinInitWorkplanTask(int id);
	@Query("select max(t.endMoment) from Workplan w join w.tasks t where w.id=?1")
	Date findMaxEndWorkplanTask(int id);

	
	@Query("select w.tasks from Workplan w where w.id=?1")
	List<Task> findTaskByWorkplan(int id);
	
}
