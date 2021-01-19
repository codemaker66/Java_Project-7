package com.nnk.springboot;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

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

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.TruncateService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserTests {

	@LocalServerPort
	private int port;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TruncateService truncateService;

	private User user = new User();

	@BeforeAll
	void init() {

		user.setUsername("username");
		user.setPassword("h4sh&dPass");
		user.setFullname("user name");
		user.setRole("USER");

	}

	@AfterAll
	void clearTable() {
		truncateService.truncateUsers();
	}

	@Test
	@Order(1)
	void createAUser() {

		// When
		user = userRepository.save(user);

		// Then
		assertThat(user.getId()).isNotNull();
		assertThat(user.getUsername()).isEqualTo("username");
	}

	@Test
	@Order(2)
	void updateAUser() {

		// Given
		user.setUsername("username99");

		// When
		user = userRepository.save(user);

		// Then
		assertThat(user.getUsername()).isEqualTo("username99");
	}

	@Test
	@Order(3)
	void findUsers() {

		// When
		List<User> listResult = userRepository.findAll();

		// Then
		assertThat(listResult.size() > 0).isTrue();
	}

	@Test
	@Order(4)
	void deleteAUser() {

		// Given
		Integer id = user.getId();

		// When
		userRepository.delete(user);

		// Then
		Optional<User> userList = userRepository.findById(id);
		assertThat(userList.isPresent()).isFalse();
	}

}
