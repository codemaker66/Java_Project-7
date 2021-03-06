package com.nnk.springboot;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
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
class BidTests {

	@LocalServerPort
	private int port;

	@Autowired
	private BidListRepository bidListRepository;

	@Autowired
	private TruncateService truncateService;

	private BidList bid = new BidList();

	@BeforeAll
	void init() {

		bid.setAccount("Account Test");
		bid.setType("Type Test");
		bid.setBidQuantity(10d);

	}

	@AfterAll
	void clearTable() {
		truncateService.truncatebidList();
	}

	@Test
	@Order(1)
	void createABid() {

		// When
		bid = bidListRepository.save(bid);

		// Then
		assertThat(bid.getBidListId()).isNotNull();
		assertThat(bid.getBidQuantity()).isEqualTo(10d);
	}

	@Test
	@Order(2)
	void updateABid() {

		// Given
		bid.setBidQuantity(20d);

		// When
		bid = bidListRepository.save(bid);

		// Then
		assertThat(bid.getBidQuantity()).isEqualTo(20d);
	}

	@Test
	@Order(3)
	void findBids() {

		// When
		List<BidList> listResult = bidListRepository.findAll();

		// Then
		assertThat(listResult.size() > 0).isTrue();
	}

	@Test
	@Order(4)
	void deleteABid() {

		// Given
		Integer id = bid.getBidListId();

		// When
		bidListRepository.delete(bid);

		// Then
		Optional<BidList> bidList = bidListRepository.findById(id);
		assertThat(bidList.isPresent()).isFalse();
	}
}
