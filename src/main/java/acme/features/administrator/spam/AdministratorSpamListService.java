package acme.features.administrator.spam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorSpamListService{

	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AdministratorSpamRepository repository;


	public List<String> findAllSpamWord(){
		List<String> result;
		
		result=this.repository.findAllSpamWord();
		
		return result;
	}
	
	public double umbral() {
		double result;
		
		result=this.repository.umbral();
		
		return result;
	}
}
