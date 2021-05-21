
package acme.testing.manager.workplan;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerWorkplanPublishTest extends AcmePlannerTest {

	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/publish-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void publishPositive(final int recordIndex, final String title, final String iniMoment, final String endMoment, final String exePeriod, final String workload, final String visibility, final String published) {
		super.signIn("managerEx", "managerEx");

		super.clickOnMenu("Manager", "List my Workplans");

		//super.checkColumnHasValue(recordIndex, 0, title);

		super.clickOnListingRecord(recordIndex);

		super.clickOnSubmitButton("Publish");

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

	}

	@ParameterizedTest
	@CsvFileSource(resources = "/manager/workplan/publish-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void publishNegative(final int recordIndex, final String title, final String iniMoment, final String endMoment, final String exePeriod, final String workload, final String visibility, final String published) {
		super.signIn("managerEx", "managerEx");

		super.clickOnMenu("Manager", "List my Workplans");

		//super.checkColumnHasValue(recordIndex, 0, title);

		super.clickOnListingRecord(recordIndex);
		super.fillInputBoxIn("end", endMoment);
		super.clickOnSubmitButton("Publish");

		super.checkErrorsExist();

		super.signOut();
	}

}
