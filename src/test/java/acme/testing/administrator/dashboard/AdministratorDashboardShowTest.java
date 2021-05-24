package acme.testing.administrator.dashboard;

import org.junit.jupiter.api.Test;

import acme.testing.AcmePlannerTest;

public class AdministratorDashboardShowTest extends AcmePlannerTest{

	
	/*
	 * Se autentica como administrador, después navega hasta la vista de la Dashboard, a continuación visualiza
	 * todos los datos que corresponden a la Dashboard
	 */
	
	@Test
	public void seeDashboard() {
		super.signIn("administrator", "administrator");
		super.clickOnMenu("Administrator", "Dashboard");
		super.signOut();
	}
}
