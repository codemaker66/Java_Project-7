package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

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
public class CurveController {

	@Autowired
	CurvePointRepository curvePointRepository;
	private static final Logger logger = LogManager.getLogger(CurveController.class);

	/**
	 * This method call the CurvePointRepository to retrieve the list of all the curvePoints.
	 * 
	 * @param model represent Java-5-specific interface that defines a holder for model attributes.
	 * @return the view that display the curvePoint list.
	 */
	@GetMapping("/curvePoint/list")
	public String home(Model model) {
		logger.info("The user requested the url : /curvePoint/list with the GET method");
		model.addAttribute("curvePoints", curvePointRepository.findAll());
		return "curvePoint/list";
	}

	/**
	 * This method display the needed view for adding a new curvePoint.
	 * 
	 * @return the view that display a form for adding a new curvePoint.
	 */
	@GetMapping("/curvePoint/add")
	public String addCurvePointForm(CurvePoint curvePoint) {
		logger.info("The user requested the url : /curvePoint/add with the GET method");
		return "curvePoint/add";
	}

	/**
	 * This method call the CurvePointRepository to add the new curvePoint to the database.
	 * 
	 * @param curvePoint is an object of type CurvePoint.
	 * @param result is a general interface that represents binding results.
	 * @param model represent Java-5-specific interface that defines a holder for model attributes.
	 * @return a redirection to the curvePoint list if the request was successful.
	 */
	@PostMapping("/curvePoint/validate")
	public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
		logger.info("The user requested the url : /curvePoint/validate with the POST method");
		if (!result.hasErrors()) {
			curvePointRepository.save(curvePoint);
			return "redirect:/curvePoint/list";
		}
		return "curvePoint/add";
	}

	/**
	 * This method display the needed view for updating a curvePoint.
	 * 
	 * @param id represent the id of the curvePoint.
	 * @param model represent Java-5-specific interface that defines a holder for model attributes.
	 * @return the view that display a form for updating the curvePoint.
	 */
	@GetMapping("/curvePoint/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		logger.info("The user requested the url : /curvePoint/update/" + id + " with the GET method");
		CurvePoint curvePoint = curvePointRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
		model.addAttribute("curvePoint", curvePoint);
		return "curvePoint/update";
	}

	/**
	 * This method call the CurvePointRepository to update a curvePoint in the database.
	 * 
	 * @param id represent the id of the curvePoint.
	 * @param curvePoint is an object of type CurvePoint.
	 * @param result is a general interface that represents binding results.
	 * @param model represent Java-5-specific interface that defines a holder for model attributes.
	 * @return a redirection to the curvePoint list if the request was successful.
	 */
	@PostMapping("/curvePoint/update/{id}")
	public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result,
			Model model) {
		logger.info("The user requested the url : /curvePoint/update/" + id + " with the POST method");
		if (result.hasErrors()) {
			return "curvePoint/update";
		}
		curvePoint.setId(id);
		curvePointRepository.save(curvePoint);
		return "redirect:/curvePoint/list";
	}

	/**
	 * This method call the CurvePointRepository to delete a curvePoint from the database.
	 * 
	 * @param id represent the id of the curvePoint.
	 * @param model represent Java-5-specific interface that defines a holder for model attributes.
	 * @return a redirection to the curvePoint list if the request was successful.
	 */
	@GetMapping("/curvePoint/delete/{id}")
	public String deleteCurvePoint(@PathVariable("id") Integer id, Model model) {
		logger.info("The user requested the url : /curvePoint/delete/" + id + " with the GET method");
		CurvePoint curvePoint = curvePointRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
		curvePointRepository.delete(curvePoint);
		return "redirect:/curvePoint/list";
	}
}
