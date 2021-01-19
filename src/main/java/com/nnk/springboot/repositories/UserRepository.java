package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

	/**
	 * This method retrieve a user by his user name.
	 * 
	 * @param username represent the user name of the user.
	 * @return an object of type User.
	 */
	@Query(value = "SELECT * FROM users WHERE users.username = :username", nativeQuery = true)
	User findByUserName(String username);

	/**
	 * This method truncate the users table from the database.
	 */
	@Modifying
	@Transactional
	@Query(value = "TRUNCATE TABLE users", nativeQuery = true)
	void truncateUsers();

}
