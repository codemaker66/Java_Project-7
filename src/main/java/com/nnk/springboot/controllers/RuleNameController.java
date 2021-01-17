package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

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
public class RuleNameController {

	@Autowired
	RuleNameRepository ruleNameRepository;
	private static final Logger logger = LogManager.getLogger(RuleNameController.class);

	@GetMapping("/ruleName/list")
	public String home(Model model) {
		logger.info("The user requested the url : /ruleName/list with the GET method");
		model.addAttribute("ruleNames", ruleNameRepository.findAll());
		return "ruleName/list";
	}

	@GetMapping("/ruleName/add")
	public String addRuleForm(RuleName bid) {
		logger.info("The user requested the url : /ruleName/add with the GET method");
		return "ruleName/add";
	}

	@PostMapping("/ruleName/validate")
	public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
		logger.info("The user requested the url : /ruleName/validate with the POST method");
		if (!result.hasErrors()) {
			ruleNameRepository.save(ruleName);
			return "redirect:/ruleName/list";
		}
		return "ruleName/add";
	}

	@GetMapping("/ruleName/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		logger.info("The user requested the url : /ruleName/update/" + id + " with the GET method");
		RuleName ruleName = ruleNameRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
		model.addAttribute("ruleName", ruleName);
		return "ruleName/update";
	}

	@PostMapping("/ruleName/update/{id}")
	public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName, BindingResult result,
			Model model) {
		logger.info("The user requested the url : /ruleName/update/" + id + " with the POST method");
		if (result.hasErrors()) {
			return "ruleName/update";
		}
		ruleName.setId(id);
		ruleNameRepository.save(ruleName);
		return "redirect:/ruleName/list";
	}

	@GetMapping("/ruleName/delete/{id}")
	public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
		logger.info("The user requested the url : /ruleName/delete/" + id + " with the GET method");
		RuleName ruleName = ruleNameRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
		ruleNameRepository.delete(ruleName);
		return "redirect:/ruleName/list";
	}
}
