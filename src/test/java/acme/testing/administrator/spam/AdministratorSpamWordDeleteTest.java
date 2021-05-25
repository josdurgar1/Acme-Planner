package acme.testing.administrator.spam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamWordDeleteTest  extends AcmePlannerTest {
	
	/*
	 * CASO POSITIVO
	 * Se autentifica como administrator, accede al menu desplegable de administrator y navega hasta la opcion "Spam List",comprobara que esta en el lugar correcto
	 * y accedera a una palabra de la lista y pulsara el boton de borrar. Una vez pulsado el boton volvera a la lista de palabras de spam y comprobara que en la posicion
	 * de la palabra que ha borrado, hay otra palabra y que se ha borrado correctamente.
	 * CASO NEGATIVO
	 * Se autentifica como manager e intenta acceder a la url de una palabra con intencion de borrarla lo que devolvera un "Access Not Authorized" cumpliendo la restriccion
	 * En el segundo negativo, se autentifica como administrator y accede a una palabra de spam, la borra y luego intenta acceder de nuevo
	 * lo cual devuelve un error ya que no se puede borrar una palabra que no existe.
	 * 
	 */
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spam-word/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void deletePositive(final int recordIndex, final String word) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "Spam List");
		
		super.clickOnListingRecord(recordIndex);

		super.clickOnSubmitButton("Delete");

		super.clickOnMenu("Administrator", "Spam List");

		super.checkColumnHasValue(recordIndex, 0, word);

		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("word", word);
		
		super.signOut();

	}
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spam-word/delete-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void deleteNegative(final int recordIndex) {
		super.signIn("manager", "manager");
		
		final String s = this.getBaseUrl();
		
		this.driver.get(s+"/administrator/spam-word/delete?id=7");
		
		super.checkErrorsExist();
		
		

	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spam-word/delete-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void deletePositive2(final int recordIndex) {
		super.signIn("administrator", "administrator");
		final String s=this.getBaseUrl();
		this.driver.get(s+"/administrator/spam-word/delete?id=19");
		super.clickOnSubmitButton("Delete");
		this.driver.get(s+"/administrator/spam-word/delete?id=19");
		super.checkErrorsExist();
		super.signOut();
	}

}
