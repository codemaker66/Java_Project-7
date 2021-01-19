package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.CurvePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CurvePointRepository extends JpaRepository<CurvePoint, Integer> {

	/**
	 * This method truncate the curvepoint table from the database.
	 */
	@Modifying
	@Transactional
	@Query(value = "TRUNCATE TABLE curvepoint", nativeQuery = true)
	void truncateCurvePoint();

}
