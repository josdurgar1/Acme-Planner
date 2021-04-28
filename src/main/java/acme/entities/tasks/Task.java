
package acme.entities.tasks;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
	public String				title;

	@Temporal(TemporalType.TIMESTAMP)
	public Date					initialMoment;

	@Temporal(TemporalType.TIMESTAMP)
	public Date					endMoment;
	
	public Double workload;

	@NotEmpty
	@Length(max = 500)
	public String				description;

	@URL
	public String				link;
	
	public boolean isFinished() {
		boolean result;
		Date now;

		now = new Date();
		result = now.after(this.endMoment);

		return result;
	}
	
	@NotNull
	public Double executionPeriod;
	
	@NotNull
	public Boolean isPublic;
	
	// Relationships -----------------------------------------------------------
	
	@NotNull
	@ManyToOne(optional=false)
	protected Manager manager;

}
