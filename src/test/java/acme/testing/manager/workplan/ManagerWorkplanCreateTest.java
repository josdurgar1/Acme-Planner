
package acme.testing.manager.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkplanCreateTest extends AcmePlannerTest {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void createPositive(final int recordIndex, final String title, final String iniMoment, final String endMoment, final String exePeriod, final String workload, final String visibility, final String published) {
		super.signIn("manager", "manager");

		super.clickOnMenu("Manager", "Create Workplan");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("init", iniMoment);
		super.fillInputBoxIn("end", endMoment);
		//			super.fillInputBoxIn("workload", workload);
		//			super.fillInputBoxIn("description", description);
		//			super.fillInputBoxIn("link", link);
		//			super.fillInputBoxIn("visibility", visibility);
		super.clickOnSubmitButton("Create");

		super.clickOnMenu("Manager", "List my Workplans");

		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, exePeriod);
		super.checkColumnHasValue(recordIndex, 2, workload);
		super.checkColumnHasValue(recordIndex, 3, iniMoment);
		super.checkColumnHasValue(recordIndex, 4, endMoment);

		super.clickOnListingRecord(recordIndex);

		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("init", iniMoment);
		super.checkInputBoxHasValue("end", endMoment);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("executionPeriod", exePeriod);
		super.checkInputBoxHasValue("isPublished", published);
		super.checkInputBoxHasValue("isPublic", visibility);

		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void createNegative(final int recordIndex, final String title, final String iniMoment, final String endMoment) {
		super.signIn("manager", "manager");

		super.clickOnMenu("Manager", "Create Workplan");
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("init", iniMoment);
		super.fillInputBoxIn("end", endMoment);
		super.clickOnSubmitButton("Create");

		super.checkErrorsExist();

		super.signOut();
	}

}
