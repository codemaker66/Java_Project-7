package com.nnk.springboot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PasswordEncodeTest {

	@LocalServerPort
	private int port;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Test
	void testPassword() {

		// When
		String password = bCryptPasswordEncoder.encode("123456");

		// Then
		assertThat(password).isNotEqualTo("123456");
		System.out.println("The hashed password is : [ " + password + " ]");
	}
}
