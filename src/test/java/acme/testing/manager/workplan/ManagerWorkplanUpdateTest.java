package acme.testing.manager.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkplanUpdateTest extends AcmePlannerTest{
	
	/*
	 * Nos logueamos como un manager, visitamos su lista de workplans, visitamos un workplan específico según el recordIndex,
	 * cambiamos los valores de los campos según el csv (todos correctos) y pulsamos actualizar. Nos reedirige a la lista de
	 * workplans de nuevo, por lo que comprobamos que el orden de los atributos es correcto y vistamos de nuevo el detalle
	 * de los workplan según el recordIndex donde comprobamos que los datos actualizados coinciden con los del csv. Por 
	 * último nos deslogueamos.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void updatePositive(final int recordIndex, final String title, final String iniMoment, final String endMoment, final String exePeriod, final String workload, final String visibility, final String published) {		
		super.signIn("managerEx", "managerEx");
		
		super.clickOnMenu("Manager", "List my Workplans");		
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("init", iniMoment);
		super.fillInputBoxIn("end", endMoment);
		super.clickOnSubmitButton("Update");
		
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
	 *Se autentica como un manager, después navega hasta la lista de sus workplan, mostramos los detalles de un workplan según
	 *el recordIndex, rellenamos los campos con datos no válidos según el csv y le damos a actualizar, comprobando que lanzan
	 *errores. Por último nos deslogueamos.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)	
	public void updateNegative(final int recordIndex, final String title, final String iniMoment, final String endMoment) {		
		super.signIn("managerEx", "managerEx");
		
		super.clickOnMenu("Manager", "List my Workplans");		
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("init", iniMoment);
		super.fillInputBoxIn("end", endMoment);
		super.clickOnSubmitButton("Update");

		super.checkErrorsExist();

		super.signOut();
	}

}
