package acme.testing.anonymous.shout;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutCreateTest extends AcmePlannerTest{
	// Lifecycle management ---------------------------------------------------

		// Test cases -------------------------------------------------------------

		@ParameterizedTest
		@CsvFileSource(resources = "/anonymous/shout/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(10)
		public void createPositive(final int recordIndex, final String author, final String text, final String info) {
			//Clickamos en el menú de anonymous y seleccionamos la subopción Shout donde nos aparecerá el formulario
			super.clickOnMenu("Anonymous", "Shout");
			//Rellenamos los campos con los parámetros que pasamos mediante el fichero .csv
			super.fillInputBoxIn("author", author);
			super.fillInputBoxIn("text", text);
			super.fillInputBoxIn("info", info);
			//Clickamos en el botón Shout! el cual creará el Shout con los datos anteriores
			super.clickOnSubmitButton("Shout!");
			//Clickamos en el menú de anonymous y seleccionamos la subopción Shout List donde nos aparecerá el listado de Shouts
			super.clickOnMenu("Anonymous", "Shout List");
			//Comprobamos que el Shout creado esté en la lista con los campos correspondientes
			super.checkColumnHasValue(recordIndex, 1, author);
			super.checkColumnHasValue(recordIndex, 2, text);

		}
		
		@ParameterizedTest
		@CsvFileSource(resources = "/anonymous/shout/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
		@Order(20)
		public void createNegative(final int recordIndex, final String author, final String text, final String info) {
			//Clickamos en el menú de anonymous y seleccionamos la subopción Shout donde nos aparecerá el formulario
			super.clickOnMenu("Anonymous", "Shout");
			//Rellenamos los campos con los parámetros que pasamos mediante el fichero .csv
			super.fillInputBoxIn("author", author);
			super.fillInputBoxIn("text", text);
			super.fillInputBoxIn("info", info);
			//Clickamos en el botón Shout! el cual creará el Shout con los datos anteriores
			super.clickOnSubmitButton("Shout!");
			//Checkeamos los distintos errores que nos dará al crear los shouts de formas negativas
			super.checkErrorsExist();
		}

		// Ancillary methods ------------------------------------------------------
}
