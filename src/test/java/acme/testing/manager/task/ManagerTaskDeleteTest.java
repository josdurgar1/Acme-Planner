package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class ManagerTaskDeleteTest extends AcmePlannerTest{
	
	// Lifecycle management -------------------------------
	
	// Test cases -----------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/delete-positive.csv", encoding = "utf-8", numLinesToSkip= 1)
	@Order(10)
	public void deletePositive(final int recordIndex, final String title, final String iniMoment, final String endMoment, final String exePeriod, final String workload, final String description, final String visibility, final String link) {
		super.signIn("managerEx", "managerEx");
		
		super.clickOnMenu("Manager", "My tasks");
		
		super.clickOnListingRecord(recordIndex);
		
		super.clickOnSubmitButton("Delete");
			
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, iniMoment);
		super.checkColumnHasValue(recordIndex, 2, endMoment);
		super.checkColumnHasValue(recordIndex, 3, exePeriod);
		super.checkColumnHasValue(recordIndex, 4, workload);
		super.checkColumnHasValue(recordIndex, 5, description);
		super.checkColumnHasValue(recordIndex, 6, visibility);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("initialMoment", iniMoment);
		super.checkInputBoxHasValue("endMoment", endMoment);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("executionPeriod", exePeriod);	
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("visibility", visibility);
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/delete-negative.csv", encoding = "utf-8", numLinesToSkip= 1)
	@Order(20)
	public void deleteNegative(final int recordIndex) {
		super.signIn("manager", "manager");
		
		final String s = this.getBaseUrl();
		this.driver.get(s+"/management/task/delete?id=42");
		super.checkErrorsExist();
		super.signOut();
		
	}
}
