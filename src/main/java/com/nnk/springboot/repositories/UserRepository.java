package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

	@Query(value = "SELECT * FROM users WHERE users.username = :username", nativeQuery = true)
	User findByUserName(String username);

}
