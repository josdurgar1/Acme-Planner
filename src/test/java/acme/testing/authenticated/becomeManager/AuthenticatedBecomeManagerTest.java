
package acme.testing.authenticated.becomeManager;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.AcmePlannerTest;

public class AuthenticatedBecomeManagerTest extends AcmePlannerTest {

	@Test
	@Order(50)
	public void becomeManager() {
		//Nos registramos con esos datos
		super.signUp("johnny", "johnny", "johnny", "johnny", "johnny@hotmail.com");
		//Nos logeamos con el nuevo usuario creado
		super.signIn("johnny", "johnny");
		//Clickamos sobre el menú Account y seleccionamos la subopción de Become a manager
		super.clickOnMenu("Account", "Become a manager");
		//Pulsamos sobre el botón Registrer para convertirnos en manager
		super.clickOnSubmitButton("Register");
		//Cerramos sesión
		super.signOut();

	}

}
