package com.proiectPS.demo;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	RestTemplate restTemplate =  new RestTemplate();
	private String usersURL = "http://localhost:4200/admin/users";

//	@Test
//	public void testRetrieveUser() throws JSONException {
//		HttpHeaders headers =  new HttpHeaders();
//		//headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
//
//		ResponseEntity<String> response = restTemplate.exchange(usersURL, HttpMethod.GET, entity, String.class);
//
//		System.out.println(response.getBody().toString());
//
//		//String expected =
//		//String expected1 = "{\"id\":1,\"name\":\"Test User\",\"email\":\"test@test.com\",\"username\":\"testUsername\",\"emailVerifiedAt\":null,\"password\":\"$2a$10$E7t2m.yqcZqA5zgjUjRDa.Uq17JPmskkEHEtcJcKMI9HXxJOZQD3S\",\"passwordConfirm\":null,\"roles\":[],\"posts\":[],\"profile\":null,\"following\":[]}";
//
//		//JSONAssert.assertEquals(expected, response.getBody(), false);
//	}
//
////	@Test
////	public  testADD(){
////
////	}

}
