package acme.testing.administrator.spam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamListTest extends AcmePlannerTest{
	
	/*
	 * Nos logueamos como administrator, navegamos hasta la lista de spam, donde tenemos el listado de 1 solo objeto de tipo
	 * umbral, comprobamos el listado, navegamos al objeto spam y comprobamos que el valor del atributo umbral es correcto.
	 * Por último nos deslogueamos.
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spam/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void list(final int recordIndex, final String umbral) {		
		super.signIn("administrator", "administrator");
		final String s=this.getBaseUrl();
		this.driver.get(s+"/administrator/spam/list");
		
		super.checkColumnHasValue(recordIndex, 0, umbral);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("umbral", umbral);
		super.signOut();
			

	}

}
