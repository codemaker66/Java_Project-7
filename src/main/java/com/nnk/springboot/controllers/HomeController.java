package com.nnk.springboot.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	private static final Logger logger = LogManager.getLogger(HomeController.class);

	/**
	 * This method display the home page view for the user.
	 * 
	 * @param model represent Java-5-specific interface that defines a holder for model attributes.
	 * @return the view that display the home page.
	 */
	@GetMapping("/")
	public String home(Model model) {
		logger.info("The user requested the home url with the GET method");
		return "home";
	}

	/**
	 * This method redirect the user to the bid list page.
	 * 
	 * @param model represent Java-5-specific interface that defines a holder for model attributes.
	 * @return the view that display the bid list page.
	 */
	@GetMapping("/admin/home")
	public String adminHome(Model model) {
		logger.info("The user requested the url : /admin/home with the GET method");
		return "redirect:/bidList/list";
	}

}
