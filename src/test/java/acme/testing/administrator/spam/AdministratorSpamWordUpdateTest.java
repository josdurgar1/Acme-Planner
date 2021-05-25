package acme.testing.administrator.spam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamWordUpdateTest extends AcmePlannerTest {
	
	/*
	 * CASO POSITIVO
	 * Se autentifica como administrator, accede al menu desplegable de administrator y navega hasta la opcion "Spam List",comprobara que esta en el lugar correcto
	 * y accedera a una de las palabras de la lista. Una vez dentro, introducira el nuevo dato valido en el campo y pulsara en actualizar. Luego accederá a la lista de palabras de Spam y accedera a la palabra actualizada comprobando
	 * que contiene los mismos valores. 
	 * CASO NEGATIVO
	 * Se autentifica como administrator, accede a la url del update para un spamWord en concreto. Una vez dentro, introducira el nuevo dato no valido en el campo y pulsara en actualizar lo cual devolvera un error cumpliendose la restriccion.
	 * 
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spam-word/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updatePositive(final int recordIndex, final String word) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Spam List");
		
		super.clickOnListingRecord(recordIndex);

		super.fillInputBoxIn("word", word);

		super.clickOnSubmitButton("Update");

		super.clickOnMenu("Administrator", "Spam List");

		super.checkColumnHasValue(recordIndex, 0, word);

		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("word", word);

	}
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spam-word/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void updateNegative(final int recordIndex, final String word) {
		super.signIn("administrator", "administrator");

		final String s= this.getBaseUrl();

		this.driver.get(s+"/administrator/spam-word/update?id=12");

		super.fillInputBoxIn("word", word);

		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();

		super.signOut();
}
}
