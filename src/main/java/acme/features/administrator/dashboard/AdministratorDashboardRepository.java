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
	
	@Query("select count(t) from Task t where visibility = PUBLIC")
	Integer totalPublicTasks();
	
	@Query("select count(t) from Task t where visibility = PRIVATE")
	Integer totalPrivateTasks();
	
	@Query("select count(t) from Task t")
	Double totalNumberOfPublicPrivateTasks();
	
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

}
