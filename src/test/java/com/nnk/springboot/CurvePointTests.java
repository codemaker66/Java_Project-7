package com.nnk.springboot;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
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
class CurvePointTests {

	@LocalServerPort
	private int port;

	@Autowired
	private CurvePointRepository curvePointRepository;

	@Autowired
	private TruncateService truncateService;

	private CurvePoint curvePoint = new CurvePoint();

	@BeforeAll
	void init() {

		curvePoint.setCurveId(10);
		curvePoint.setTerm(10d);
		curvePoint.setValue(30d);

	}

	@AfterAll
	void clearTable() {
		truncateService.truncateCurvePoint();
	}

	@Test
	@Order(1)
	void createACurvePoint() {

		// When
		curvePoint = curvePointRepository.save(curvePoint);

		// Then
		assertThat(curvePoint.getId()).isNotNull();
		assertThat(curvePoint.getCurveId()).isEqualTo(10);
	}

	@Test
	@Order(2)
	void updateACurvePoint() {

		// Given
		curvePoint.setCurveId(20);

		// When
		curvePoint = curvePointRepository.save(curvePoint);

		// Then
		assertThat(curvePoint.getCurveId()).isEqualTo(20);
	}

	@Test
	@Order(3)
	void findCurvePoints() {

		// When
		List<CurvePoint> listResult = curvePointRepository.findAll();

		// Then
		assertThat(listResult.size() > 0).isTrue();

	}

	@Test
	@Order(4)
	void deleteACurvePoint() {

		// Given
		Integer id = curvePoint.getId();

		// When
		curvePointRepository.delete(curvePoint);

		// Then
		Optional<CurvePoint> curvePointList = curvePointRepository.findById(id);
		assertThat(curvePointList.isPresent()).isFalse();
	}

}
