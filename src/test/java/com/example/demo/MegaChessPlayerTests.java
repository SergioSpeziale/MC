package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import request.Message;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MegaChessPlayerTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testGetConnectedUsersRequest() {
		Message message = new Message("get_user_list", new HashMap<String, String>());
		assertEquals(message.getAction(), "get_user_list");
		assertNotNull(message.getData());		
	}	
	
	
	@Test
	void testGetConnectedUsersResponse() {
		String[] users = {"Sergio","Sole"};
		HashMap<String, String[]> data = new HashMap<String, String[]>();
		data.put("user_list", users);
		/*
		GetConnectedUsersResponse response = new GetConnectedUsersResponse("update_user_list", data);
		assertEquals(response.getEvent(), "update_user_list");
		assertEquals(response.getData().get("user_list"), users);
		*/
	}
	
}
