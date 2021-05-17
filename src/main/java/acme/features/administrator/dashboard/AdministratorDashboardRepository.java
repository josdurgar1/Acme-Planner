/*
 * AdministratorDashboardRepository.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {
	
	//--------------------------------------TASK---------------------------------------------
	@Query("select count(t) from Task t where t.visibility = 0")
	Integer totalPublicTasks();
	
	@Query("select count(t) from Task t where t.visibility = 1")
	Integer totalPrivateTasks();
	
	@Query("select count(t) from Task t")
	Integer totalNumberOfPublicPrivateTasks();
	
	@Query("select count(t) from Task t where t.endMoment < current_timestamp()")
	Integer totalNonFinishedTasks();
	
	@Query("select count(t) from Task t where t.endMoment > current_timestamp()")
	Integer totalFinishedTasks();
	
	@Query("select count(t) from Task t")
	Double totalNumberOfFinishedNonFinishedTasks();
	
	@Query("select avg(DATEDIFF(t.endMoment, t.initialMoment)) from Task t")
	Double averageNumberOfTaskExecutionPeriods();
	
	@Query("select stddev(DATEDIFF(t.endMoment, t.initialMoment)) from Task t")
	Double stdDevTaskExecutionPeriods();
	
	@Query("select min(DATEDIFF(t.endMoment, t.initialMoment)) from Task t")
	Integer minExecutionPeriod();

	@Query("select max(DATEDIFF(t.endMoment, t.initialMoment)) from Task t")
	Integer maxExecutionPeriod();
	
	@Query("select max(t.workload) from Task t")
	Double maxWorkload();
	
	@Query("select min(t.workload) from Task t")
	Double minWorkload();
	
	@Query("select avg(t.workload) from Task t")
	Double averageNumberOfTaskWorkloads();
	
	@Query("select stddev(t.workload) from Task t")
	Double stdDevTaskWorkloads();
	

	//CHARTS
	
	@Query("select count(w) from Workplan w")
	Integer totalNumberWorkplans();
	
	@Query("select count(w) from Workplan w where w.isPublished=true")
	Integer totalNumberWorkplansPublished();
	
	@Query("select count(w) from Workplan w where w.isPublished=false")
	Integer totalNumberWorkplansNonPublished();

	//--------------------------------------WORKPLAN---------------------------------------------
	@Query("select count(t) from Workplan t where t.isPublic = true")
	Integer totalPublicWorkplan();
	
	@Query("select count(t) from Workplan t where t.isPublic = false")
	Integer totalPrivateWorkplan();
	
	@Query("select count(t) from Workplan t")
	Integer totalNumberOfPublicPrivateWorkplan();
	
	@Query("select count(t) from Workplan t where t.end < current_timestamp()")
	Integer totalNonFinishedWorkplan();
	
	@Query("select count(t) from Workplan t where t.end > current_timestamp()")
	Integer totalFinishedWorkplan();
	
	@Query("select count(t) from Workplan t")
	Double totalNumberOfFinishedNonFinishedWorkplan();
	
	@Query("select avg(DATEDIFF(t.end, t.init)) from Workplan t")
	Double averageNumberOfWorkplanExecutionPeriods();
	
	@Query("select stddev(DATEDIFF(t.end, t.init)) from Workplan t")
	Double stdDevWorkplanExecutionPeriods();
	
	@Query("select min(DATEDIFF(t.end, t.init)) from Workplan t")
	Integer minWorkplanExecutionPeriod();

	@Query("select max(DATEDIFF(t.end, t.init)) from Workplan t")
	Integer maxWorkplanExecutionPeriod();
	
	@Query("select max(t.workload) from Workplan t")
	Double maxWorkplanWorkload();
	
	@Query("select min(t.workload) from Workplan t")
	Double minWorkplanWorkload();
	
	@Query("select avg(t.workload) from Workplan t")
	Double averageNumberOfWorkplanWorkloads();
	
	@Query("select stddev(t.workload) from Workplan t")
	Double stdDevWorkplanWorkloads();


}
