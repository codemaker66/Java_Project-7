package com.nnk.springboot;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.TruncateService;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TradeTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TradeRepository tradeRepository;

	@Autowired
	private TruncateService truncateService;

	private Trade trade = new Trade();

	@BeforeAll
	void init() {

		trade.setAccount("Trade Account");
		trade.setType("Type");

	}

	@AfterAll
	void clearTable() {
		truncateService.truncateTrade();
	}

	@Test
	@Order(1)
	void createATrade() {

		// When
		trade = tradeRepository.save(trade);

		// Then
		assertThat(trade.getTradeId()).isNotNull();
		assertThat(trade.getAccount()).isEqualTo("Trade Account");
	}

	@Test
	@Order(2)
	void updateATrade() {

		// Given
		trade.setAccount("Trade Account Update");

		// When
		trade = tradeRepository.save(trade);

		// Then
		assertThat(trade.getAccount()).isEqualTo("Trade Account Update");
	}

	@Test
	@Order(3)
	void findTrades() {

		// When
		List<Trade> listResult = tradeRepository.findAll();

		// Then
		assertThat(listResult.size() > 0).isTrue();
	}

	@Test
	@Order(4)
	void deleteATrade() {

		// Given
		Integer id = trade.getTradeId();

		// When
		tradeRepository.delete(trade);

		// Then
		Optional<Trade> tradeList = tradeRepository.findById(id);
		assertThat(tradeList.isPresent()).isFalse();
	}
}
