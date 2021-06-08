package acme.features.anonymous.shout;
import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.shouts.Shout;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousShoutRepository extends AbstractRepository {
	
	@Query("select s from Shout s where s.moment >= :fechaActualUnMesAntes order by s.moment DESC")
	Collection<Shout> findMany(Date fechaActualUnMesAntes);
	
//	@Query("select s from Shout s where DATEDIFF(s.moment, current_timestamp()) < 30")
//	Coll getExecutionPeriod();
}
