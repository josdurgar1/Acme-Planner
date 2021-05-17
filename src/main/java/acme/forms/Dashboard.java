package acme.forms;



import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes TASKS-------------------------------------------------------------

	Integer						totalPublicTasks;
	Integer						totalPrivateTasks;
	Integer						totalNumberOfPublicPrivateTasks;
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
	

	// Attributes WORKPLANS-------------------------------------------------------------
	
	Integer						totalPublicWorkplan;
	Integer						totalPrivateWorkplan;
	Integer						totalNumberOfPublicPrivateWorkplan;
	Integer						totalNonFinishedWorkplan;
	Integer						totalFinishedWorkplan;
	Double						totalNumberOfFinishedNonFinishedWorkplan;
	Double						averageNumberOfWorkplanExecutionPeriods;
	Double						stdDevWorkplanExecutionPeriods;
	Integer						minWorkplanExecutionPeriod;
	Integer						maxWorkplanExecutionPeriod;
	Double 						maxWorkplanWorkload;
	Double 						minWorkplanWorkload;
	Double 						averageNumberOfWorkplanWorkloads;
	Double 						stdDevWorkplanWorkloads;
	
  	//CHART ATTRIBUTES
  Integer						totalNumberWorkplans;
	Integer						totalNumberWorkplansPublished;
	Integer						totalNumberWorkplansNonPublished;
	

// Derived attributes -----------------------------------------------------

// Relationships ----------------------------------------------------------

}