package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.Valid;

@Controller
public class BidListController {

	@Autowired
	BidListRepository bidListRepository;
	private static final Logger logger = LogManager.getLogger(BidListController.class);

	/**
	 * This method call the bidListRepository to retrieve the list of all the bids.
	 * 
	 * @param model represent Java-5-specific interface that defines a holder for model attributes.
	 * @return the view that display the bid list.
	 */
	@GetMapping("/bidList/list")
	public String home(Model model) {
		logger.info("The user requested the url : /bidList/list with the GET method");
		model.addAttribute("bidLists", bidListRepository.findAll());
		return "bidList/list";
	}

	/**
	 * This method display the needed view for adding a new bid.
	 * 
	 * @return the view that display a form for adding a new bid.
	 */
	@GetMapping("/bidList/add")
	public String addBidForm(BidList bidList) {
		logger.info("The user requested the url : /bidList/add with the GET method");
		return "bidList/add";
	}

	/**
	 * This method call the bidListRepository to add the new bid to the database.
	 * 
	 * @param bid is an object of type BidList.
	 * @param result is a general interface that represents binding results.
	 * @param model represent Java-5-specific interface that defines a holder for model attributes.
	 * @return a redirection to the bid list if the request was successful.
	 */
	@PostMapping("/bidList/validate")
	public String validate(@Valid BidList bid, BindingResult result, Model model) {
		logger.info("The user requested the url : /bidList/validate with the POST method");
		if (!result.hasErrors()) {
			bidListRepository.save(bid);
			return "redirect:/bidList/list";
		}
		return "bidList/add";
	}

	/**
	 * This method display the needed view for updating a bid.
	 * 
	 * @param id represent the id of the bid.
	 * @param model represent Java-5-specific interface that defines a holder for model attributes.
	 * @return the view that display a form for updating the bid.
	 */
	@GetMapping("/bidList/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		logger.info("The user requested the url : /bidList/update/" + id + " with the GET method");
		BidList bidList = bidListRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
		model.addAttribute("bidList", bidList);
		return "bidList/update";
	}

	/**
	 * This method call the bidListRepository to update a bid in the database.
	 * 
	 * @param id represent the id of the bid.
	 * @param bidList is an object of type BidList.
	 * @param result is a general interface that represents binding results.
	 * @param model represent Java-5-specific interface that defines a holder for model attributes.
	 * @return a redirection to the bid list if the request was successful.
	 */
	@PostMapping("/bidList/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result, Model model) {
		logger.info("The user requested the url : /bidList/update/" + id + " with the POST method");
		if (result.hasErrors()) {
			return "bidList/update";
		}
		bidList.setBidListId(id);
		bidListRepository.save(bidList);
		return "redirect:/bidList/list";
	}

	/**
	 * This method call the bidListRepository to delete a bid from the database.
	 * 
	 * @param id represent the id of the bid.
	 * @param model represent Java-5-specific interface that defines a holder for model attributes.
	 * @return a redirection to the bid list if the request was successful.
	 */
	@GetMapping("/bidList/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {
		logger.info("The user requested the url : /bidList/delete/" + id + " with the GET method");
		BidList bidList = bidListRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
		bidListRepository.delete(bidList);
		return "redirect:/bidList/list";
	}
}
