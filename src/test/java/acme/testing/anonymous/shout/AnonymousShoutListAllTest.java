package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutListAllTest extends AcmePlannerTest{

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/list-all.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void listAll(final int recordIndex, final String moment, final String author, final String text) {		
		//Clickamos en el menú de anonymous y seleccionamos la subopción Shout List donde nos aparecerá el listado de Shouts
		super.clickOnMenu("Anonymous", "Shout List");
		//Comprobamos que los datos son los esperados
		super.checkColumnHasValue(recordIndex, 0, moment);
		super.checkColumnHasValue(recordIndex, 1, author);
		super.checkColumnHasValue(recordIndex, 2, text);
		
		

	}

	// Ancillary methods ------------------------------------------------------
}
