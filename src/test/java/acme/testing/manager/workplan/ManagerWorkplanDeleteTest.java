
package acme.testing.manager.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkplanDeleteTest extends AcmePlannerTest {

	/*
	 * Se loguea como un manager, lista sus workplans, posteriormente navega para ver los detalles del workplan que le indicamos
	 * según el recordIndex y le damos al botón de eliminar. Posteriormente comprobamos que en la lista, al volver a cargar el
	 * mismo workplan según el recordIndex, el workplan que se carga es el siguiente, por lo que el anterior se borró
	 * correctamente. Por último se desloguea.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void deletePositive(final int recordIndex, final String title, final String iniMoment, final String endMoment, final String exePeriod, final String workload, final String visibility, final String published) {
		super.signIn("managerEx", "managerEx");

		super.clickOnMenu("Manager", "List my Workplans");

		super.clickOnListingRecord(recordIndex);

		super.clickOnSubmitButton("Delete");

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
	 * Nos autenticamos como un manager, visitamos su lista de workplans, cargamos en detalle un workplan y lo publicamos,
	 * a continuación navagamos hasta la pagina de eliminación del workplan que hemos publicado y le damos al botón de eliminar.
	 * Posteriormente comprobamos que hay errores ya que no se puede eliminar un workplan que está publicado. Por último
	 * nos delogueamos.
	 */

	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/delete-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void deleteNegative(final int recordIndex) {
		super.signIn("managerEx", "managerEx");

		super.clickOnMenu("Manager", "List my Workplans");
		super.clickOnListingRecord(recordIndex);
		super.clickOnSubmitButton("Publish");
		final String s=this.getBaseUrl();
		this.driver.get(s+"/management/workplan/delete?id=53");
		super.clickOnSubmitButton("Delete");
		super.checkErrorsExist();
		super.signOut();
	}

}
