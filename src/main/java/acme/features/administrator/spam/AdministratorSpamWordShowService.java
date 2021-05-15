package acme.features.administrator.spam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.SpamWord;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorSpamWordShowService implements AbstractShowService<Administrator, SpamWord> {
	
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
	public SpamWord findOne(final Request<SpamWord> request) {
		assert request != null;
		final int spamWordId;
		
		spamWordId = request.getModel().getInteger("id");
	
		
		return this.repository.findOne(spamWordId);
	}

}
