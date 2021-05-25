/*
 * EmployerJobCreateTest.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskCreateTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	/* CASO POSITIVO
	 * Se autentifica como manager, accede al menu desplegable de manager y navega hasta la opcion de crear tarea, una vez dentro, introduce los valores
	 * proporcionados en el archivo .csv los cuales son validos y pulsa el boton de crear. Una vez creada, accede a la lista de tareas propias, comprueba
	 * que esta en la lista de las tareas y accede a la tarea creada y comprueba que los datos introducidos son los mismos.
	 * CASO NEGATIVO
	 * Se autentifica como manager, accede al menu desplegable de manager y navega hasta la opcion de crear tarea, una vez dentro, introduce los valores
	 * proporcionados en el archivo .csv los cuales no son validos para comprobar que se cumplen todas las restricciones, una vez introducidos los datos, 
	 * intenta crear una tarea y devuelve errores por lo no realiza acciones ilegales.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String title, final String iniMoment, final String endMoment, final String exePeriod, final String workload, final String description, final String visibility, final String link) {
		super.signIn("manager", "manager");

		super.clickOnMenu("Manager", "Create Task");

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("initialMoment", iniMoment);
		super.fillInputBoxIn("endMoment", endMoment);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("visibility", visibility);
		
		super.clickOnSubmitButton("Create");

		super.clickOnMenu("Manager", "My tasks");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, iniMoment);
		super.checkColumnHasValue(recordIndex, 2, endMoment);
		super.checkColumnHasValue(recordIndex, 3, exePeriod);
		super.checkColumnHasValue(recordIndex, 4, workload);
		super.checkColumnHasValue(recordIndex, 5, description);
		super.checkColumnHasValue(recordIndex, 6, visibility);

		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("initialMoment", iniMoment);
		super.checkInputBoxHasValue("endMoment", endMoment);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("executionPeriod", exePeriod);	
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("visibility", visibility);

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createNegative(final int recordIndex, final String title, final String iniMoment, final String endMoment, final String exePeriod, final String workload, final String description, final String link, final String visibility) {
		super.signIn("manager", "manager");

		super.clickOnMenu("Manager", "Create Task");

		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("initialMoment", iniMoment);
		super.fillInputBoxIn("endMoment", endMoment);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("link", link);
		super.fillInputBoxIn("visibility", visibility);
		super.clickOnSubmitButton("Create");

		super.checkErrorsExist();

		super.signOut();
	}

	// Ancillary methods ------------------------------------------------------

}
