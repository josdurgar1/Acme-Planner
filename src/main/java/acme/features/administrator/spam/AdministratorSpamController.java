package acme.features.administrator.spam;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.spam.Spam;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

//@Controller
//@RequestMapping("administrator/spam/")
public class AdministratorSpamController extends AbstractController<Administrator, Spam>{
	
	@Autowired
	protected AdministratorSpamListService listService;
	
	
	@PostConstruct
	protected void initialise() {
		//super.addBasicCommand(BasicCommand.LIST, this.listService);
	}

}
