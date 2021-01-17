package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

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
public class TradeController {

	@Autowired
	TradeRepository tradeRepository;
	private static final Logger logger = LogManager.getLogger(TradeController.class);

	@GetMapping("/trade/list")
	public String home(Model model) {
		logger.info("The user requested the url : /trade/list with the GET method");
		model.addAttribute("trades", tradeRepository.findAll());
		return "trade/list";
	}

	@GetMapping("/trade/add")
	public String addUser(Trade bid) {
		logger.info("The user requested the url : /trade/add with the GET method");
		return "trade/add";
	}

	@PostMapping("/trade/validate")
	public String validate(@Valid Trade trade, BindingResult result, Model model) {
		logger.info("The user requested the url : /trade/validate with the POST method");
		if (!result.hasErrors()) {
			tradeRepository.save(trade);
			return "redirect:/trade/list";
		}
		return "trade/add";
	}

	@GetMapping("/trade/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		logger.info("The user requested the url : /trade/update/" + id + " with the GET method");
		Trade trade = tradeRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
		model.addAttribute("trade", trade);
		return "trade/update";
	}

	@PostMapping("/trade/update/{id}")
	public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade, BindingResult result, Model model) {
		logger.info("The user requested the url : /trade/update/" + id + " with the POST method");
		if (result.hasErrors()) {
			return "trade/update";
		}
		trade.setTradeId(id);
		tradeRepository.save(trade);
		return "redirect:/trade/list";
	}

	@GetMapping("/trade/delete/{id}")
	public String deleteTrade(@PathVariable("id") Integer id, Model model) {
		logger.info("The user requested the url : /trade/delete/" + id + " with the GET method");
		Trade trade = tradeRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
		tradeRepository.delete(trade);
		return "redirect:/trade/list";
	}
}
