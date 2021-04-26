package acme.entities.workplan;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import acme.entities.tasks.Task;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Workplan extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	@NotNull
	protected Boolean isPublic;
	
	@NotNull
	protected Double executionPeriod;
	
	@Digits(integer = 3, fraction = 2)
	protected Double workload;
	
	@Temporal(TemporalType.TIMESTAMP)
	protected Date					init;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date					end;
	
	// Relationship -----------------------------------------------------------
	
	@Valid
	@ManyToMany(fetch = FetchType.EAGER)
	protected Collection<Task> tasks;

}