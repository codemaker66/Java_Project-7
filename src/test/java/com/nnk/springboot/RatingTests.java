package com.nnk.springboot;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
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
class RatingTests {

	@LocalServerPort
	private int port;

	@Autowired
	private RatingRepository ratingRepository;

	@Autowired
	private TruncateService truncateService;

	private Rating rating = new Rating();

	@BeforeAll
	void init() {

		rating.setMoodysRating("Moodys Rating");
		rating.setSandPRating("Sand PRating");
		rating.setFitchRating("Fitch Rating");
		rating.setOrderNumber(10);

	}

	@AfterAll
	void clearTable() {
		truncateService.truncateRating();
	}

	@Test
	@Order(1)
	void createARating() {

		// When
		rating = ratingRepository.save(rating);

		// Then
		assertThat(rating.getId()).isNotNull();
		assertThat(rating.getOrderNumber()).isEqualTo(10);
	}

	@Test
	@Order(2)
	void updateARating() {

		// Given
		rating.setOrderNumber(20);

		// When
		rating = ratingRepository.save(rating);

		// Then
		assertThat(rating.getOrderNumber()).isEqualTo(20);
	}

	@Test
	@Order(3)
	void findRatings() {

		// When
		List<Rating> listResult = ratingRepository.findAll();

		// Then
		assertThat(listResult.size() > 0).isTrue();
	}

	@Test
	@Order(4)
	void deleteARating() {

		// Given
		Integer id = rating.getId();

		// When
		ratingRepository.delete(rating);

		// Then
		Optional<Rating> ratingList = ratingRepository.findById(id);
		assertThat(ratingList.isPresent()).isFalse();
	}
}
