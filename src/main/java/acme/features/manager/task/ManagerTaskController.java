package acme.features.manager.task;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.roles.Manager;
import acme.entities.tasks.Task;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/management/task/")
public class ManagerTaskController extends AbstractController<Manager, Task>{
	// Internal state ---------------------------------------------------------

					
			@Autowired
			protected ManagerTaskShowService		showService;
			
			@Autowired
			protected ManagerTaskCreateService		createService;
			
			@Autowired
			protected ManagerTaskDeleteService		deleteService;
			
			@Autowired
			protected ManagerTaskUpdateService		updateService;
			
			@Autowired 
			protected ManagerTaskListMineService 	listMineService;
			
			
			// Constructors -----------------------------------------------------------


			@PostConstruct
			protected void initialise() {
				super.addBasicCommand(BasicCommand.SHOW, this.showService);
				super.addBasicCommand(BasicCommand.CREATE, this.createService);
				super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
				super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
				
				
				super.addCustomCommand(CustomCommand.LIST_MINE,BasicCommand.LIST,this.listMineService);

			}
}
