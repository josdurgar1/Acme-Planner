package acme.features.administrator.spam;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.spam.Spam;
import acme.entities.spam.SpamWord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorSpamRepository extends AbstractRepository {
	
	
	@Query("select s.word from SpamWord s")
	List<String> findAllSpamWord();
	
	@Query("select s from SpamWord s")
	List<SpamWord> findAllSpamWordObjects();

	@Query("select s.umbral from Spam s")
	double umbral();
	
	@Query("select spam from Spam spam")
	List<Spam> findAllSpam();
	
	@Query("select spam from Spam spam")
	Spam findSpam();
	
	@Query("select s from SpamWord s where s.id = ?1")
	SpamWord findOne(int id);
	
	
}
