package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TradeRepository extends JpaRepository<Trade, Integer> {

	/**
	 * This method truncate the trade table from the database.
	 */
	@Modifying
	@Transactional
	@Query(value = "TRUNCATE TABLE trade", nativeQuery = true)
	void truncateTrade();

}
