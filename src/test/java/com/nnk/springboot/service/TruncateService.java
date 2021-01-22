package com.nnk.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.repositories.UserRepository;

@Service
public class TruncateService {

	@Autowired
	private BidListRepository bidListRepository;

	@Autowired
	private CurvePointRepository curvePointRepository;

	@Autowired
	private RatingRepository ratingRepository;

	@Autowired
	private RuleNameRepository ruleNameRepository;

	@Autowired
	private TradeRepository tradeRepository;

	@Autowired
	private UserRepository userRepository;
	

	public void truncatebidList() {
		bidListRepository.truncateBidList();
	}

	public void truncateCurvePoint() {
		curvePointRepository.truncateCurvePoint();
	}

	public void truncateRating() {
		ratingRepository.truncateRating();
	}

	public void truncateRuleName() {
		ruleNameRepository.truncateRuleName();
	}

	public void truncateTrade() {
		tradeRepository.truncateTrade();
	}

	public void truncateUsers() {
		userRepository.truncateUsers();
	}

}
