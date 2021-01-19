package com.nnk.springboot;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
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
class RuleTests {

	@LocalServerPort
	private int port;

	@Autowired
	private RuleNameRepository ruleNameRepository;

	@Autowired
	private TruncateService truncateService;

	private RuleName rule = new RuleName();

	@BeforeAll
	void init() {

		rule.setName("Rule Name");
		rule.setDescription("Description");
		rule.setJson("Json");
		rule.setTemplate("Template");
		rule.setSqlStr("SQL");
		rule.setSqlPart("SQL Part");

	}

	@AfterAll
	void clearTable() {
		truncateService.truncateRuleName();
	}

	@Test
	@Order(1)
	void createARuleName() {

		// When
		rule = ruleNameRepository.save(rule);

		// Then
		assertThat(rule.getId()).isNotNull();
		assertThat(rule.getName()).isEqualTo("Rule Name");
	}

	@Test
	@Order(2)
	void updateARuleName() {

		// Given
		rule.setName("Rule Name Update");

		// When
		rule = ruleNameRepository.save(rule);

		// Then
		assertThat(rule.getName()).isEqualTo("Rule Name Update");
	}

	@Test
	@Order(3)
	void findRuleNames() {

		// When
		List<RuleName> listResult = ruleNameRepository.findAll();

		// Then
		assertThat(listResult.size() > 0).isTrue();
	}

	@Test
	@Order(4)
	void deleteARuleName() {

		// Given
		Integer id = rule.getId();

		// When
		ruleNameRepository.delete(rule);

		// Then
		Optional<RuleName> ruleList = ruleNameRepository.findById(id);
		assertThat(ruleList.isPresent()).isFalse();
	}
}
