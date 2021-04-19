package acme.forms;



import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	Integer						totalPublicTasks;
	Integer						totalPrivateTasks;
	Double						totalNumberOfPublicPrivateTasks;
	Integer						totalNonFinishedTasks;
	Integer						totalFinishedTasks;
	Double						totalNumberOfFinishedNonFinishedTasks;
	Double						averageNumberOfTaskExecutionPeriods;
	Double						stdDevTaskExecutionPeriods;
	Integer						minExecutionPeriod;
	Integer						maxExecutionPeriod;
	Double 						maxWorkload;
	Double 						minWorkload;
	Double 						averageNumberOfTaskWorkloads;
	Double 						stdDevTaskWorkloads;


	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}