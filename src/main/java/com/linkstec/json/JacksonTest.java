package com.linkstec.json;

import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTest {

	public static void main(String[] args) {
		ObjectMapper om = new ObjectMapper();
		JsonForTest jsonForTest = new JsonForTest();
		jsonForTest.setBusId("ddd  dd");
		Date date = new Date();
		jsonForTest.setDate(date);
		String jsonString = null;
		try {
			jsonString = om.writeValueAsString(jsonForTest);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(jsonString);

	}

}
