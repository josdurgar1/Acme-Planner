
package acme.testing.manager.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkplanPublishTest extends AcmePlannerTest {

	/*
	 * Nos logueamos como un manager, listamos sus workplans, mostramos el show plan según el recordIndex, a continuación pulsa
	 * el botón de publicar. Vuelve a cargar la lista de workplans, comprueba el orden de los atributos y muestra el workplan 
	 * según el recordIndex de nuevo donde comprueba que los datos son correctos y el workplan ha sido publicado. Por último
	 * se desloguea.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/publish-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void publishPositive(final int recordIndex, final String title, final String iniMoment, final String endMoment, final String exePeriod, final String workload, final String visibility, final String published) {
		super.signIn("managerEx", "managerEx");

		super.clickOnMenu("Manager", "List my Workplans");

		super.clickOnListingRecord(recordIndex);

		super.clickOnSubmitButton("Publish");

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
	 * Se loguea como un manager, navega hasta el listado de sus workplans, muestra un workplan según el recordIndex, cambiamos
	 * algunos de los campos y lo dejamos vacio o con datos incorrectos, en este caso dejamos una fecha vacia y le damos al
	 * botón publicar. Como el campo no es correcto, lanza un error. Por último nos deslogueamos.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/publish-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void publishNegative(final int recordIndex, final String title, final String iniMoment, final String endMoment, final String exePeriod, final String workload, final String visibility, final String published) {
		super.signIn("managerEx", "managerEx");

		super.clickOnMenu("Manager", "List my Workplans");

		super.clickOnListingRecord(recordIndex);
		super.fillInputBoxIn("end", endMoment);
		super.clickOnSubmitButton("Publish");

		super.checkErrorsExist();

		super.signOut();
	}

}
