
package acme.features.anonymous.shout;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.shouts.Shout;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousShoutListService implements AbstractListService<Anonymous, Shout> {

	// Internal state ---------------------------------------------------------

	@Autowired

	protected AnonymousShoutRepository repository;

	// AbstractListService<Administrator, Shout> interface --------------

	@Override

	public boolean authorise(final Request<Shout> request) {
		assert request != null;
		return true;

	}

	@Override

	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "author", "text", "moment");
	}

	@Override

	public Collection<Shout> findMany(final Request<Shout> request) {
		assert request != null;
		Collection<Shout> result;
		final Calendar calendario = Calendar.getInstance();
		calendario.add(Calendar.MONTH, -1);
		final Date fechaActualUnMesAntes = calendario.getTime();
		result = this.repository.findMany(fechaActualUnMesAntes);
		return result;

	}

}
