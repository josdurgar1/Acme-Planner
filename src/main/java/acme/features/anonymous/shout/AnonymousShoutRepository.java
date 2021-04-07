package acme.features.anonymous.shout;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.shouts.Shout;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousShoutRepository extends AbstractRepository {
	
//	@Query("select s from Shout s order by s.moment DESC")
	@Query("select s from Shout s where DATEDIFF(current_timestamp(), s.moment) < 30")
	Collection<Shout> findMany();
	
//	@Query("select s from Shout s where DATEDIFF(s.moment, current_timestamp()) < 30")
//	Coll getExecutionPeriod();
}
