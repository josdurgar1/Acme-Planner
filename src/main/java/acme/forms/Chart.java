package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Chart implements Serializable {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	Integer				totalNumberWorkplans;
	Integer				totalNumberWorkplansPublished;
	Integer				totalNumberWorkplansNonPublished;

}
