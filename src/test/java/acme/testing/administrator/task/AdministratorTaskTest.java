package acme.testing.administrator.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorTaskTest extends AcmePlannerTest{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/task/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void list(final int recordIndex, final String title, final String initialMoment, final String endMoment,
		final String workload, final String executionPeriod, final String description, final String link, final String visibility) {	
		//Iniciamos sesión como administrador
		super.signIn("administrator", "administrator");
		//Clickamos en el menú de Authenticated y clickamos en la subopción de Tasks List donde se mostrará el listado de tareas
		super.clickOnMenu("Authenticated", "Tasks List");
		//Comprobamos que los datos son los esperados
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, initialMoment);
		super.checkColumnHasValue(recordIndex, 2, endMoment);
		super.checkColumnHasValue(recordIndex, 3, description);
		//Clickamos en la tarea con ese index para verla con más detalle, hacia la vista del show
		super.clickOnListingRecord(recordIndex);
		//Comprobamos que los datos son los esperados
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("initialMoment", initialMoment);
		super.checkInputBoxHasValue("endMoment", endMoment);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("executionPeriod", executionPeriod);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("visibility", visibility);
		super.checkInputBoxHasValue("link", link);
		//Cerramos sesión
		super.signOut();
	}
}