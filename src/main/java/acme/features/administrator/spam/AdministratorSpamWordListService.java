package acme.features.administrator.spam;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.SpamWord;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorSpamWordListService implements AbstractListService<Administrator, SpamWord>{

	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AdministratorSpamRepository repository;
		
		
	@Override
	public boolean authorise(final Request<SpamWord> request) {
		assert request != null;
		return true;
	}
	
	@Override
	public void unbind(final Request<SpamWord> request, final SpamWord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "word");
	}
	
	@Override
	public Collection<SpamWord> findMany(final Request<SpamWord> request) {
		assert request  != null;
		//final List<SpamWord> result;
		//result = this.repository.findAllSpamWordObjects();
		return this.repository.findAllSpamWordObjects();
	}


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
