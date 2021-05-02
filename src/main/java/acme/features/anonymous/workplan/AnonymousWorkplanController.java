package acme.features.anonymous.workplan;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.workplan.Workplan;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/workplan/")
public class AnonymousWorkplanController extends AbstractController<Anonymous, Workplan>{
	// Internal state ---------------------------------------------------------

			@Autowired
			protected AnonymousWorkplanListService		listService;
			
			@Autowired
			protected AnonymousWorkplanShowService		showService;


			// Constructors -----------------------------------------------------------


			@PostConstruct
			protected void initialise() {

				super.addBasicCommand(BasicCommand.LIST, this.listService);
				super.addBasicCommand(BasicCommand.SHOW, this.showService);
}
}
