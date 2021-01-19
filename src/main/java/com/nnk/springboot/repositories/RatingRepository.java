package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

	/**
	 * This method truncate the rating table from the database.
	 */
	@Modifying
	@Transactional
	@Query(value = "TRUNCATE TABLE rating", nativeQuery = true)
	void truncateRating();

}
