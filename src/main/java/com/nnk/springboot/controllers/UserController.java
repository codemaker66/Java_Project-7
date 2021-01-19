package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;
	private static final Logger logger = LogManager.getLogger(UserController.class);

	/**
	 * This method call the UserRepository to retrieve the list of all the users.
	 * 
	 * @param model represent Java-5-specific interface that defines a holder for model attributes.
	 * @return the view that display the user list.
	 */
	@GetMapping("/user/list")
	public String home(Model model) {
		logger.info("The user requested the url : /user/list with the GET method");
		model.addAttribute("users", userRepository.findAll());
		return "user/list";
	}

	/**
	 * This method display the needed view for adding a new user.
	 * 
	 * @return the view that display a form for adding a new user.
	 */
	@GetMapping("/user/add")
	public String addUser() {
		logger.info("The user requested the url : /user/add with the GET method");
		return "user/add";
	}

	/**
	 * This method call the UserRepository to add the new user to the database.
	 * 
	 * @param user is an object of type User.
	 * @param result is a general interface that represents binding results.
	 * @param model represent Java-5-specific interface that defines a holder for model attributes.
	 * @return a redirection to the user list if the request was successful.
	 */
	@PostMapping("/user/validate")
	public String validate(@Valid User user, BindingResult result, Model model) {
		logger.info("The user requested the url : /user/validate with the POST method");
		if (!result.hasErrors()) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			user.setPassword(encoder.encode(user.getPassword()));
			userRepository.save(user);
			return "redirect:/user/list";
		}
		return "user/add";
	}

	/**
	 * This method display the needed view for updating a user.
	 * 
	 * @param id represent the id of the user.
	 * @param model represent Java-5-specific interface that defines a holder for model attributes.
	 * @return the view that display a form for updating the user.
	 */
	@GetMapping("/user/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		logger.info("The user requested the url : /user/update/" + id + " with the GET method");
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		user.setPassword("");
		model.addAttribute("user", user);
		return "user/update";
	}

	/**
	 * This method call the UserRepository to update a user in the database.
	 * 
	 * @param id represent the id of the user.
	 * @param user is an object of type User.
	 * @param result is a general interface that represents binding results.
	 * @param model represent Java-5-specific interface that defines a holder for model attributes.
	 * @return a redirection to the user list if the request was successful.
	 */
	@PostMapping("/user/update/{id}")
	public String updateUser(@PathVariable("id") Integer id, @Valid User user, BindingResult result, Model model) {
		logger.info("The user requested the url : /user/update/" + id + " with the POST method");
		if (result.hasErrors()) {
			return "user/update";
		}

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		user.setId(id);
		userRepository.save(user);
		return "redirect:/user/list";
	}

	/**
	 * This method call the UserRepository to delete a user from the database.
	 * 
	 * @param id represent the id of the user.
	 * @param model represent Java-5-specific interface that defines a holder for model attributes.
	 * @return a redirection to the user list if the request was successful.
	 */
	@GetMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable("id") Integer id, Model model) {
		logger.info("The user requested the url : /user/delete/" + id + " with the GET method");
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		userRepository.delete(user);
		return "redirect:/user/list";
	}
}
