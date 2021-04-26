package acme.features.manager.workplan;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.workplan.Workplan;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/manager/workplan/")
public class ManagerWorkplanController extends AbstractController<Authenticated, Workplan>{
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected ManagerWorkplanCreateService	createService;
		@Autowired
		protected ManagerWorkplanListService		listService;
		@Autowired
		protected ManagerWorkplanShowService		showService;
		@Autowired
		protected ManagerWorkplanPublishService	publishService;
		@Autowired
		protected ManagerWorkplanUpdateService	updateService;

		// Constructors -----------------------------------------------------------


		@PostConstruct
		protected void initialise() {
			super.addBasicCommand(BasicCommand.CREATE, this.createService);
			super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
			super.addBasicCommand(BasicCommand.LIST, this.listService);
			super.addBasicCommand(BasicCommand.SHOW, this.showService);
			
			super.addCustomCommand(CustomCommand.PUBLISH, BasicCommand.UPDATE, this.publishService);
		}

}
