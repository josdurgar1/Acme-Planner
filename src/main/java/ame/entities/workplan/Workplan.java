package ame.entities.workplan;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	protected Double workload;
	
	// Relationship -----------------------------------------------------------
	
	@ManyToMany(mappedBy = "task_id", fetch = FetchType.EAGER)
	@JoinTable(name = "workplan_Tasks", joinColumns = @JoinColumn(name = "workplan_id"),
	inverseJoinColumns = @JoinColumn(name = "task_id"))
	protected List<Task> task;

}
