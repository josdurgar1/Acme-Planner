package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutCreateTest extends AcmePlannerTest{
	// Lifecycle management ---------------------------------------------------

		// Test cases -------------------------------------------------------------

	/*
	 * Navega hasta crear un Shout a través del meno Anonymous, a continuación carga cos datos válidos
	 * los campos, y envía el formulario. Posteriormente, lista los shouts y comprueba que la lista
	 * tiene los elementos correctos.
	 * En los casos negativos comprobará que cumple con el filtro de Spam y que los campos Text y Author no estén vacíos.
	 */
	
		@ParameterizedTest
		@CsvFileSource(resources = "/anonymous/shout/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void createPositive(final int recordIndex, final String author, final String text, final String info) {
			
			super.clickOnMenu("Anonymous", "Shout");
			
			super.fillInputBoxIn("author", author);
			super.fillInputBoxIn("text", text);
			super.fillInputBoxIn("info", info);
			
			super.clickOnSubmitButton("Shout!");

			super.clickOnMenu("Anonymous", "Shout List");
			
			super.checkColumnHasValue(recordIndex, 1, author);
			super.checkColumnHasValue(recordIndex, 2, text);

		}
		
		@ParameterizedTest
		@CsvFileSource(resources = "/anonymous/shout/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(20)
		public void createNegative(final int recordIndex, final String author, final String text, final String info) {
			
			super.clickOnMenu("Anonymous", "Shout");
			
			super.fillInputBoxIn("author", author);
			super.fillInputBoxIn("text", text);
			super.fillInputBoxIn("info", info);
			
			super.clickOnSubmitButton("Shout!");
			
			super.checkErrorsExist();
		}

		// Ancillary methods ------------------------------------------------------
}
