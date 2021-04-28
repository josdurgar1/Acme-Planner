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

package acme.features.administrator.chart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Chart;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorChartShowService implements AbstractShowService<Administrator, Chart> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorChartRepository repository;

	// AbstractShowService<Administrator, Chart> interface ----------------


	@Override
	public boolean authorise(final Request<Chart> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Chart> request, final Chart entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "totalNumberWorkplans","totalNumberWorkplansPublished" ,"totalNumberWorkplansNonPublished");
	}

	@Override
	public Chart findOne(final Request<Chart> request) {
		assert request != null;

		Chart result;
		Integer	totalNumberWorkplans;
		Integer totalNumberWorkplansPublished;
		Integer totalNumberWorkplansNonPublished;
		


		totalNumberWorkplans = this.repository.totalNumberWorkplans();
		totalNumberWorkplansPublished = this.repository.totalNumberWorkplansPublished();
		totalNumberWorkplansNonPublished = this.repository.totalNumberWorkplansNonPublished();
		
		


		result = new Chart();
		result.setTotalNumberWorkplans(totalNumberWorkplans);
		result.setTotalNumberWorkplansPublished(totalNumberWorkplansPublished);
		result.setTotalNumberWorkplansNonPublished(totalNumberWorkplansNonPublished);


		return result;
	}
	

}
