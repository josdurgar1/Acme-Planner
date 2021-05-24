package acme.testing.administrator.spam;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AdministratorSpamUpdateTest extends AcmePlannerTest{
	
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

		super.clickOnMenu("Administrator", "Spam Threshold");
		
		super.fillInputBoxIn("umbral", umbral);

		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();

}
}
