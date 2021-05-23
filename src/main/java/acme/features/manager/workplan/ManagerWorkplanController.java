package acme.features.manager.workplan;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.roles.Manager;
import acme.entities.workplan.Workplan;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/management/workplan/")
public class ManagerWorkplanController extends AbstractController<Manager, Workplan>{
	
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
		@Autowired
		protected ManagerWorkplanDeleteService deleteService;
		@Autowired
		protected ManagerWorkplanUnnassignService unnassignService;

		// Constructors -----------------------------------------------------------


		@PostConstruct
		protected void initialise() {
			super.addBasicCommand(BasicCommand.CREATE, this.createService);
			super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
			super.addBasicCommand(BasicCommand.LIST, this.listService);
			super.addBasicCommand(BasicCommand.SHOW, this.showService);
			super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
			
			super.addCustomCommand(CustomCommand.PUBLISH, BasicCommand.UPDATE, this.publishService);
			super.addCustomCommand(CustomCommand.UNNASSIGN, BasicCommand.UPDATE, this.unnassignService);
		}

}
