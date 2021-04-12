
package acme.entities.tasks;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

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
//
//	public Double getWorkload(){
//		return (double) (this.endMoment.getTime() - this.initialMoment.getTime()) / 3600000;
//	}
	

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
	
	public Double executionPeriod;

}
