package acme.testing.administrator.dashboard;

import org.junit.jupiter.api.Test;

import acme.testing.AcmePlannerTest;

public class AdministratorDashboardShowTest extends AcmePlannerTest{

	@Test
	public void seeDashboard() {
		//Nos logeamos como administrador
		super.signIn("administrator", "administrator");
		//Pulsamos sobre el menú de administrador y clickamos en la subopción Dashboard para ver la dashboard
		super.clickOnMenu("Administrator", "Dashboard");
		//Cerramos sesión
		super.signOut();
	}
}
