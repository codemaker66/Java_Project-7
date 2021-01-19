package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BidListRepository extends JpaRepository<BidList, Integer> {

	/**
	 * This method truncate the bidlist table from the database.
	 */
	@Modifying
	@Transactional
	@Query(value = "TRUNCATE TABLE bidlist", nativeQuery = true)
	void truncateBidList();

}
