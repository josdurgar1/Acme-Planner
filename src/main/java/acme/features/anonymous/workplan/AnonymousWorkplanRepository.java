package acme.features.anonymous.workplan;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.workplan.Workplan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousWorkplanRepository extends AbstractRepository{
	@Query("select w from Workplan w where w.end > current_timestamp() and w.isPublic=true order by w.workload DESC")
	Collection<Workplan> findMany();
	
	@Query("select w from Workplan w where w.id = ?1 and w.isPublic=true and w.end > current_timestamp()")
	Workplan findOneWorkplanById(int id);
	
	@Query("select DATEDIFF(w.end, w.init) from Workplan w where w.id LIKE ?1")
	Double getExecutionPeriod(int id);
}
