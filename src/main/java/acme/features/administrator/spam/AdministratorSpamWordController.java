package acme.features.administrator.spam;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.spam.SpamWord;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("administrator/spam-word/")
public class AdministratorSpamWordController extends AbstractController<Administrator, SpamWord>{

	@Autowired
	protected AdministratorSpamWordCreateService createService;
	
	@Autowired
	protected AdministratorSpamWordListService listService;
	
	@Autowired
	protected AdministratorSpamWordShowService showService;
	
	@Autowired
	protected AdministratorSpamWordDeleteService deleteService;
	
	@Autowired 
	protected AdministratorSpamWordUpdateService updateService;
	
	
	
	
	@PostConstruct
	protected void initialise() {
		
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);

	}
}
