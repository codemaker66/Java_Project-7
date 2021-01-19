package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RatingController {

	@Autowired
	RatingRepository ratingRepository;
	private static final Logger logger = LogManager.getLogger(RatingController.class);

	/**
	 * This method call the RatingRepository to retrieve the list of all the ratings.
	 * 
	 * @param model represent Java-5-specific interface that defines a holder for model attributes.
	 * @return the view that display the rating list.
	 */
	@GetMapping("/rating/list")
	public String home(Model model) {
		logger.info("The user requested the url : /rating/list with the GET method");
		model.addAttribute("ratings", ratingRepository.findAll());
		return "rating/list";
	}

	/**
	 * This method display the needed view for adding a new rating.
	 * 
	 * @return the view that display a form for adding a new rating.
	 */
	@GetMapping("/rating/add")
	public String addRatingForm() {
		logger.info("The user requested the url : /rating/add with the GET method");
		return "rating/add";
	}

	/**
	 * This method call the RatingRepository to add the new rating to the database.
	 * 
	 * @param rating is an object of type Rating.
	 * @param result is a general interface that represents binding results.
	 * @param model represent Java-5-specific interface that defines a holder for model attributes.
	 * @return a redirection to the rating list if the request was successful.
	 */
	@PostMapping("/rating/validate")
	public String validate(@Valid Rating rating, BindingResult result, Model model) {
		logger.info("The user requested the url : /rating/validate with the POST method");
		if (!result.hasErrors()) {
			ratingRepository.save(rating);
			return "redirect:/rating/list";
		}
		return "rating/add";
	}

	/**
	 * This method display the needed view for updating a rating.
	 * 
	 * @param id represent the id of the rating.
	 * @param model represent Java-5-specific interface that defines a holder for model attributes.
	 * @return the view that display a form for updating the rating.
	 */
	@GetMapping("/rating/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		logger.info("The user requested the url : /rating/update/" + id + " with the GET method");
		Rating rating = ratingRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
		model.addAttribute("rating", rating);
		return "rating/update";
	}

	/**
	 * This method call the RatingRepository to update a rating in the database.
	 * 
	 * @param id represent the id of the rating.
	 * @param rating is an object of type Rating.
	 * @param result is a general interface that represents binding results.
	 * @param model represent Java-5-specific interface that defines a holder for model attributes.
	 * @return a redirection to the rating list if the request was successful.
	 */
	@PostMapping("/rating/update/{id}")
	public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result,
			Model model) {
		logger.info("The user requested the url : /rating/update/" + id + " with the POST method");
		if (result.hasErrors()) {
			return "rating/update";
		}
		rating.setId(id);
		ratingRepository.save(rating);
		return "redirect:/rating/list";
	}

	/**
	 * This method call the RatingRepository to delete a rating from the database.
	 * 
	 * @param id represent the id of the rating.
	 * @param model represent Java-5-specific interface that defines a holder for model attributes.
	 * @return a redirection to the rating list if the request was successful.
	 */
	@GetMapping("/rating/delete/{id}")
	public String deleteRating(@PathVariable("id") Integer id, Model model) {
		logger.info("The user requested the url : /rating/delete/" + id + " with the GET method");
		Rating rating = ratingRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
		ratingRepository.delete(rating);
		return "redirect:/rating/list";
	}
}
