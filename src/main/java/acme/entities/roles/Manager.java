package acme.entities.roles;

import javax.validation.constraints.NotBlank;

import acme.framework.entities.UserRole;

public class Manager extends UserRole {
	// Serialisation identifier -----------------------------------------------

		protected static final long	serialVersionUID	= 1L;

		// Attributes -------------------------------------------------------------

		@NotBlank
		protected String			name;


		// Derived attributes -----------------------------------------------------

		// Relationships ----------------------------------------------------------
}
