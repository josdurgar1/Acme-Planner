package acme.testing.anonymous.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousWorkplanListTest extends AcmePlannerTest{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/workplan/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void list(final int recordIndex, final String title, final String isPublic, final String isPublished, 
		final String init, final String end, final String workload, final String executionPeriod) {
		//Clickamos en el menú de anonymous y seleccionamos la subopción Workplan List donde nos aparecerá el listado de Workplans
		super.clickOnMenu("Anonymous", "Workplan List");
		//Comprobamos que los datos son los esperados
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, isPublic);
		super.checkColumnHasValue(recordIndex, 2, isPublished);
		super.checkColumnHasValue(recordIndex, 3, init);
		super.checkColumnHasValue(recordIndex, 4, end);
		super.checkColumnHasValue(recordIndex, 5, workload);
		//Clickamos en el workplan con ese index para verlo con más detalle, hacia la vista del show
		super.clickOnListingRecord(recordIndex);
		//Comprobamos que los datos son los esperados
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("isPublic", isPublic);
		super.checkInputBoxHasValue("isPublished", isPublished);
		super.checkInputBoxHasValue("executionPeriod", executionPeriod);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("init", init);	
		super.checkInputBoxHasValue("end", end);
	}
}
