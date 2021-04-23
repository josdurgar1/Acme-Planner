package acme.features.administrator.spam;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorSpamRepository extends AbstractRepository {
	
	
	@Query("select s.word from SpamWord s")
	List<String> findAllSpamWord();

	@Query("select s.umbral from Spam s")
	double umbral();
	
}
