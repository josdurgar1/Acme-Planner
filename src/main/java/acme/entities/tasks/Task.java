
package acme.entities.tasks;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.roles.Manager;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Task extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotEmpty
	@Length(max = 80)
	protected String				title;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date					initialMoment;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date					endMoment;
	
	@Digits(integer = 3, fraction = 2)
	protected Double workload;

	@NotEmpty
	@Length(max = 500)
	protected String				description;

	@URL
	protected String				link;
	
	public boolean isFinished() {
		boolean result;
		Date now;

		now = new Date(System.currentTimeMillis());
		result = now.after(this.endMoment);
		return result;
	}
	
	@NotNull
	protected Double executionPeriod;
	
	@NotNull
	protected TaskVisibility visibility;
	
	// Relationships -----------------------------------------------------------
	
	@Valid
	@NotNull
	@ManyToOne(optional=false)
	protected Manager manager;

}
