package acme.testing.administrator.spam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamWordDeleteTest  extends AcmePlannerTest {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/spam-word/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
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
	@Order(10)
	public void deleteNegative(final int recordIndex) {
		super.signIn("manager", "manager");
		
		final String s = this.getBaseUrl();
		
		this.driver.get(s+"/administrator/spam-word/delete?id=7");
		
		super.checkErrorsExist();

	}

}
