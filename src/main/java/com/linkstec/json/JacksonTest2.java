package com.linkstec.json;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTest2 {

	public static void main(String[] args) {
		ObjectMapper om = new ObjectMapper();
		JsonForTest jsonForTest = new JsonForTest();
		jsonForTest.setBusId("ddd  dd");
		jsonForTest.setDdoubl(222.00);
		jsonForTest.setBigDecimal(new BigDecimal("222.22"));
		Date date = new Date();
		jsonForTest.setDate(date);
		String jsonString = null;
		try {
			jsonString = om.writeValueAsString(jsonForTest);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(jsonString);
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++=");
		try {
			@SuppressWarnings("unchecked")
			Map<String, Object> receiveMap=om.readValue(jsonString, HashMap.class);
			Double doubl11 = (Double)(receiveMap.get("doubl"));
			System.out.println(doubl11);
			Double doubl22 = (Double)(receiveMap.get("bigDecimal"));
			System.out.println(doubl22);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
