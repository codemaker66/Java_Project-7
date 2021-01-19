package com.nnk.springboot.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("app")
public class LoginController {

	private static final Logger logger = LogManager.getLogger(LoginController.class);

	/**
	 * This method display the login form.
	 * 
	 * @return the view that display the login form.
	 */
	@GetMapping("login")
	public ModelAndView login() {
		logger.info("The user requested the login url with the GET method");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}

	/**
	 * This method display the error page.
	 * 
	 * @return the view that display the error page.
	 */
	@GetMapping("error")
	public ModelAndView error() {
		logger.info("The user was redirected to the error page");
		ModelAndView mav = new ModelAndView();
		String errorMessage = "You are not authorized for the requested data.";
		mav.addObject("errorMsg", errorMessage);
		mav.setViewName("403");
		return mav;
	}
}
