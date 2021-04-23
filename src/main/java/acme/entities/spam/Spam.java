package acme.entities.spam;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Spam extends DomainEntity{
	
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	
	@NotNull
	@Range(min=0, max=100)
	protected double umbral; 
	
	// Relationships ----------------------------------------------------------

	@Valid
	@OneToMany
	protected Collection<SpamWord> spamWords;
}
