package acme.features.manager.workplan;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Manager;
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

}
