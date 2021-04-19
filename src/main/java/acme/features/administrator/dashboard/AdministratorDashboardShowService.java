/*
 * AdministratorDashboardShowService.java
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorDashboardRepository repository;

	// AbstractShowService<Administrator, Dashboard> interface ----------------


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "totalPublicTasks","totalPrivateTasks" ,"totalNumberOfPublicPrivateTasks","averageNumberOfTaskExecutionPeriods", "stdDevTaskExecutionPeriods", "totalFinishedTasks","totalNonFinishedTasks","totalNumberOfFinishedNonFinishedTasks",
			"minExecutionPeriod", "maxExecutionPeriod", "maxWorkload", "minWorkload", "averageNumberOfTaskWorkloads", "stdDevTaskWorkloads");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		Dashboard result;
		final Integer	totalPublicTasks;
		final Integer	totalPrivateTasks;
		Double totalNumberOfPublicPrivateTasks;
		Double averageNumberOfTaskExecutionPeriods;
		final Integer totalFinishedTasks;
		final Integer totalNonFinishedTasks;
		Double totalNumberOfFinishedNonFinishedTasks;
		Double getStdDevTaskExecutionPeriods;
		Integer minExecutionPeriod;
		Integer maxExecutionPeriod;
		final Double  maxWorkload;
		final Double  minWorkload;
		final Double 	averageNumberOfTaskWorkloads;
		final Double 	stdDevTaskWorkloads;


		totalPublicTasks = this.repository.totalPublicTasks();
		totalPrivateTasks = this.repository.totalPrivateTasks();
		totalNumberOfPublicPrivateTasks = this.repository.totalNumberOfPublicPrivateTasks();
		averageNumberOfTaskExecutionPeriods = this.repository.averageNumberOfTaskExecutionPeriods();
		totalFinishedTasks = this.repository.totalFinishedTasks();
		totalNonFinishedTasks = this.repository.totalNonFinishedTasks();
		totalNumberOfFinishedNonFinishedTasks = this.repository.totalNumberOfFinishedNonFinishedTasks();
		getStdDevTaskExecutionPeriods = this.repository.stdDevTaskExecutionPeriods();
		minExecutionPeriod = this.repository.minExecutionPeriod();
		maxExecutionPeriod = this.repository.maxExecutionPeriod();
		maxWorkload = this.repository.maxWorkload();
		minWorkload = this.repository.minWorkload();
		averageNumberOfTaskWorkloads = this.repository.averageNumberOfTaskWorkloads();
		stdDevTaskWorkloads = this.repository.stdDevTaskWorkloads();
		
		


		result = new Dashboard();
		result.setTotalPublicTasks(totalPublicTasks);
		result.setTotalPrivateTasks(totalPrivateTasks);
		result.setTotalNumberOfPublicPrivateTasks(totalNumberOfPublicPrivateTasks);
		result.setAverageNumberOfTaskExecutionPeriods(averageNumberOfTaskExecutionPeriods);
		result.setTotalFinishedTasks(totalFinishedTasks);
		result.setTotalNonFinishedTasks(totalNonFinishedTasks);
		result.setTotalNumberOfFinishedNonFinishedTasks(totalNumberOfFinishedNonFinishedTasks);
		result.setStdDevTaskExecutionPeriods(getStdDevTaskExecutionPeriods);
		result.setMinExecutionPeriod(minExecutionPeriod);
		result.setMaxExecutionPeriod(maxExecutionPeriod);
		result.setMaxWorkload(maxWorkload);
		result.setMaxWorkload(maxWorkload);
		result.setMinWorkload(minWorkload);
		result.setAverageNumberOfTaskWorkloads(averageNumberOfTaskWorkloads);
		result.setStdDevTaskWorkloads(stdDevTaskWorkloads);


		return result;
	}
	

}
