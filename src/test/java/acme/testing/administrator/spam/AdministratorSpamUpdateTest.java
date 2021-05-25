package acme.testing.administrator.spam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamUpdateTest extends AcmePlannerTest{
	
	/* CASO POSITIVO
	 * Se autentifica como administrator, accede al menu desplegable de administrator y navega hasta Spam Threshold, una vez dentro, comprueba que esta en el lugar correcto
	 * y procede a modificar el umbral por un valor correcto proporcionado por el archivo .csv
	 * CASO NEGATIVO
	 * Se autentifica como administrator, accede a la url para actualizar el umbral, procede a modificar el umbral por valores incorrectos proporcionados por el archivo .csv devolviendo errores ya que se cumplen las restricciones.
	 * 
	 */
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spam/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updatePositive(final int recordIndex, final String umbral) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Spam Threshold");
		
		super.fillInputBoxIn("umbral", umbral);

		super.clickOnSubmitButton("Update");

		super.clickOnMenu("Administrator", "Spam Threshold");

		super.checkInputBoxHasValue("umbral", umbral);

	}
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spam/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void updateNegative(final int recordIndex, final String umbral) {
		super.signIn("administrator", "administrator");
		
		final String s=super.getBaseUrl();
		super.driver.get(s+"/administrator/spam/update?id=20");
		
		super.fillInputBoxIn("umbral", umbral);

		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();

}
}
