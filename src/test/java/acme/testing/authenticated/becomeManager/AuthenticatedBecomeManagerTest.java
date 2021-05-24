
package acme.testing.authenticated.becomeManager;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import acme.testing.AcmePlannerTest;

public class AuthenticatedBecomeManagerTest extends AcmePlannerTest {

	@Test
	@Order(50)
	public void becomeManager() {
		
		super.signUp("johnny", "johnny", "johnny", "johnny", "johnny@hotmail.com");
		super.signIn("johnny", "johnny");
		super.clickOnMenu("Account", "Become a manager");
		super.clickOnSubmitButton("Register");
		super.signOut();

	}

}
