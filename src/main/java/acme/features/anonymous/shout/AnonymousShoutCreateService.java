
package acme.features.anonymous.shout;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.shouts.Shout;
import acme.features.administrator.spam.AdministratorSpamWordListService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;
import acme.spam.SpamRead;

@Service
public class AnonymousShoutCreateService implements AbstractCreateService<Anonymous, Shout> {

	// Internal state ---------------------------------------------------------

	@Autowired

	protected AnonymousShoutRepository repository;
	
	// Other Services--------------------
	@Autowired
	protected AdministratorSpamWordListService spamService;

	// AbstractCreateService<Administrator, Shout> interface --------------


	@Override

	public boolean authorise(final Request<Shout> request) {
		assert request != null;
		return true;
		
	}

	@Override

	public void bind(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);

	}

	@Override

	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "author", "text", "info");
		
	}

	@Override

	public Shout instantiate(final Request<Shout> request) {
		assert request != null;
		Shout result;
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		result = new Shout();
		result.setAuthor("John Doe");
		result.setText("Lorem ipsum!");
		result.setMoment(moment);
		result.setInfo("http://example.org");
		return result;

	}

	@Override

	public void validate(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		List<String> spamList;
		double umbral;
		umbral= this.spamService.umbral();
		spamList=this.spamService.findAllSpamWord();
		assert errors != null;
		if (!errors.hasErrors("text")){
			final boolean res=SpamRead.isSpam(umbral, entity.getText(), spamList);
			
			errors.state(request, !res, "text", "anonymous.shout.form.error.spam");
			}
		
//		if (!errors.hasErrors("author")){
//			final boolean res=SpamRead.isSpam(umbral, entity.getAuthor(), spamList);
//			
//			errors.state(request, !res, "author", "anonymous.shout.form.error.spam");
//			}
//		if (!errors.hasErrors("info")){
//			final boolean res=SpamRead.isSpam(umbral, entity.getInfo(), spamList);
//			
//			errors.state(request, !res, "info", "anonymous.shout.form.error.spam");
//			}
		
	}

	@Override

	public void create(final Request<Shout> request, final Shout entity) {
		assert request != null;
		assert entity != null;
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);

	}

}
