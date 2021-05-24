
package acme.testing.manager.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkplanCreateTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	/*
	 * Se autentica como un manager, después navega hasta Crear un Workplan, a continuación carga cos datos válidos
	 * los campos, y envía el formulario. Posteriormente, lista los workplans del manager y comprueba que la lista
	 * tiene los elementos correctos, para seguir visitando cada vista de cada workplan comprobando que se han guardado
	 * los datos correctamente. Por último se desloguea de la aplicación.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String title, final String iniMoment, final String endMoment, final String exePeriod, final String workload, final String visibility, final String published) {
		super.signIn("manager", "manager");

		super.clickOnMenu("Manager", "Create Workplan");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("init", iniMoment);
		super.fillInputBoxIn("end", endMoment);
		super.clickOnSubmitButton("Create");

		super.clickOnMenu("Manager", "List my Workplans");

		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, exePeriod);
		super.checkColumnHasValue(recordIndex, 2, workload);
		super.checkColumnHasValue(recordIndex, 3, iniMoment);
		super.checkColumnHasValue(recordIndex, 4, endMoment);

		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("init", iniMoment);
		super.checkInputBoxHasValue("end", endMoment);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("executionPeriod", exePeriod);
		super.checkInputBoxHasValue("isPublished", published);
		super.checkInputBoxHasValue("isPublic", visibility);

		super.signOut();
	}
	
	/*
	 * Se autentica como manager, continua navegando al menu para crear un workplan, en cada prueba inserta algún
	 * dato que contiene alguna restricción, enviando el formulario con esos errores y posteriormente comprobando que 
	 * lanza los errores oportunos. Por último se desloguea.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createNegative(final int recordIndex, final String title, final String iniMoment, final String endMoment) {
		super.signIn("manager", "manager");

		super.clickOnMenu("Manager", "Create Workplan");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("init", iniMoment);
		super.fillInputBoxIn("end", endMoment);
		super.clickOnSubmitButton("Create");

		super.checkErrorsExist();

		super.signOut();
	}

}
