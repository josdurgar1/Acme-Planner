package acme.testing.administrator.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorTaskTest extends AcmePlannerTest{
	
	/*
	 * Se autentica como administrador, después navega hasta listar las Tasks, a continuación comprueba que la lista
	 * tiene los elementos correctos, para seguir visitando cada vista de cada tarea comprobando que los datos
	 * son correctos. Por último se desloguea de la aplicación.
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/task/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void list(final int recordIndex, final String title, final String initialMoment, final String endMoment,
		final String workload, final String executionPeriod, final String description, final String link, final String visibility) {	
		
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Authenticated", "Tasks List");
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, initialMoment);
		super.checkColumnHasValue(recordIndex, 2, endMoment);
		super.checkColumnHasValue(recordIndex, 3, description);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("initialMoment", initialMoment);
		super.checkInputBoxHasValue("endMoment", endMoment);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("executionPeriod", executionPeriod);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("visibility", visibility);
		super.checkInputBoxHasValue("link", link);
		
		super.signOut();
	}
}