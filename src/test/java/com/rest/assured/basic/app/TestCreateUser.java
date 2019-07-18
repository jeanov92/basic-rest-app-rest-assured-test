package com.rest.assured.basic.app;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

class TestCreateUser {

	private final String CONTEXT_PATH = "/basic-rest-app";

	@BeforeEach
	void setUp() throws Exception {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
	}

	@Test
	void createUser() {
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("id", 100);
		requestBody.put("age", 47);
		requestBody.put("name", "Zinedine Zidane");

		Response response = given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post(CONTEXT_PATH + "/user")
				.then()
				.statusCode(201)
				.extract()
				.response();

		assertNotNull(response);
		assertEquals(new Integer(100), response.jsonPath().get("id"));
		assertEquals(new Integer(47), response.jsonPath().get("age"));
		assertEquals("Zinedine Zidane", response.jsonPath().get("name"));
		
	}

}
