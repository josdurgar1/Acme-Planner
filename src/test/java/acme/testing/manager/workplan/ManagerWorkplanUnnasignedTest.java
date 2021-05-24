
package acme.testing.manager.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmePlannerTest;

public class ManagerWorkplanUnnasignedTest extends AcmePlannerTest {

	/*
	 *Nos autenticamos como un manager, navegamos hasta la lista de sus workplans, a continuación mostramos un workplan según
	 *el recordIndex, posteriormente pulsamos el botón desasignar (en este caso el workplan solo tiene una tarea asignada), 
	 *despues volvemos a cargar la lista de workplans, comprobamos el orden de los atributos y volvemos a mostrar el workplan,
	 *donde comprobamos que los datos son los correctos y que no existe el botón de desasignar debido a que la única tarea que
	 *tenía asignada se desasignó correctamente. Por último nos deslogueamos.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/unnasign-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void unnasignedPositive(final int recordIndex, final String title, final String iniMoment, final String endMoment, final String exePeriod, final String workload, final String visibility, final String published) {
		super.signIn("managerEx", "managerEx");

		super.clickOnMenu("Manager", "List my Workplans");

		super.clickOnListingRecord(recordIndex);
		super.clickOnSubmitButton("Unnasign");

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
		super.checkNotExists(By.name("Unnasign"));
		super.signOut();
	}
	
	
	/*
	 * Nos logueamos como un manager, navegamos hacia el listado de sus workplans, mostramos un workplan específico según
	 * el recordIndex comprobando que existe el botón desasignar, después copiamos la dirección de esa vista, nos desloguemos
	 * y nos logueamos con otro manager, intetamos acceder a la vista anterior y comprobamos que no podemos. Por último nos 
	 * deslogueamos.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/unnasign-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void unnasignedNegative(final int recordIndex) {
		super.signIn("managerEx", "managerEx");

		super.clickOnMenu("Manager", "List my Workplans");

		super.clickOnListingRecord(recordIndex);
		super.checkButtonExists("Unnasign");
		final String url=super.driver.getCurrentUrl();
		super.signOut();
		super.signIn("manager", "manager");
		super.driver.get(url);
		super.checkErrorsExist();

		super.signOut();
	}

}
