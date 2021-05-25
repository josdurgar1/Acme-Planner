package acme.features.administrator.spam;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.Spam;
import acme.entities.spam.SpamWord;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractDeleteService;

@Service
public class AdministratorSpamWordDeleteService implements AbstractDeleteService<Administrator, SpamWord>{
	
	@Autowired
	protected AdministratorSpamRepository repository;

	@Override
	public boolean authorise(final Request<SpamWord> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<SpamWord> request, final SpamWord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
		
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

	@Override
	public void validate(final Request<SpamWord> request, final SpamWord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void delete(final Request<SpamWord> request, final SpamWord entity) {
		assert request != null;
		assert entity != null;
		final Spam s = this.repository.findSpam();
		final Collection<SpamWord> csw = s.getSpamWords();
		csw.remove(entity);
		this.repository.delete(entity);
		
	}

}
