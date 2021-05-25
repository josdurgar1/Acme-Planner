
package acme.testing.administrator.spam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamWordCreateTest extends AcmePlannerTest {
	
	/*
	 * CASO POSITIVO
	 * Se autentifica como administrator, accede al menu desplegable de administrator y navega hasta la opcion "Add Spamword",comprobara que esta en el lugar correcto
	 * e introducira un valor valido y pulsara en añadir. Una vez añadida la palabra, accederá a la lista de palabras de Spam y accedera a la palabra creada comprobando
	 * que contiene los mismos valores. 
	 * CASO NEGATIVO
	 * Se autentifica como administrator, accede al menu desplegable de administrator y navega hasta la opcion "Add Spamword",comprobara que esta en el lugar correcto
	 * e introducira un valor no valido, lo que devolvera un error comprobando asi que se cumplen las restricciones. 
	 * 
	 */

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spam-word/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String word) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Add SpamWord");

		super.fillInputBoxIn("word", word);

		super.clickOnSubmitButton("Add");

		super.clickOnMenu("Administrator", "Spam List");

		super.checkColumnHasValue(recordIndex, 0, word);

		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("word", word);

		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spam-word/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createNegative(final int recordIndex, final String word) {

		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Add SpamWord");

		super.fillInputBoxIn("word", word);

		super.clickOnSubmitButton("Add");
		
		super.checkErrorsExist();

	}
}
