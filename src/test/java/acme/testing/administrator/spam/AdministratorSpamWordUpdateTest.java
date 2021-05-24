package acme.testing.administrator.spam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamWordUpdateTest extends AcmePlannerTest {
	
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

		super.clickOnMenu("Administrator", "Spam List");
		
		super.clickOnListingRecord(recordIndex);

		super.fillInputBoxIn("word", word);

		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();

		super.signOut();
}
}
