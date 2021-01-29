package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.RuleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface RuleNameRepository extends JpaRepository<RuleName, Integer> {

	/**
	 * This method truncate the rulename table from the database.
	 */
	@Modifying
	@Transactional
	@Query(value = "TRUNCATE TABLE rulename", nativeQuery = true)
	void truncateRuleName();

}
