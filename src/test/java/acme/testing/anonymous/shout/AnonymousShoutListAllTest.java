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
	public void listAll(final int recordIndex, final String author, final String text, final String moment) {		

		super.clickOnMenu("Anonymous", "Shout List");
		
		super.checkColumnHasValue(recordIndex, 0, author);
		super.checkColumnHasValue(recordIndex, 1, text);
		super.checkColumnHasValue(recordIndex, 2, moment);
		
		

	}

	// Ancillary methods ------------------------------------------------------
}
