package com.linkstec.json;

import java.util.Date;

import net.sf.json.JSONObject;

/**
 * 
 * @author liuyl
 *
 */
public class JsonLibAppTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JsonForTest jsonForTest = new JsonForTest();
		JsonMsgContent JsonMsgContent = new JsonMsgContent();
		String msgContent = JSONObject.fromObject(JsonMsgContent).toString();
		jsonForTest.setMsgContent(msgContent);
		
		Date date = new Date();
		jsonForTest.setDate(date);
		
		//1.POJO to jsonObject
		JSONObject jsonObject1 = (JSONObject) JSONObject.fromObject(jsonForTest);
		System.out.println("++++++++1.pojo to jsonObject++++++++++++++++++++++++++++++++++++++++");
		System.out.println(jsonObject1);
		System.out.println("++++++++2.pojo to  String++++++++++++++++++++++++++++++++++++++++");
		//2.POJO to String
//		String jsonFor1 = JSONObject.(jsonForTest);
//		System.out.println(jsonFor1);

		//3.JSONObject to String
		System.out.println("++++++++3.jsonObject to  String++++++++++++++++++++++++++++++++++++++++");
		String jsonFor1 = jsonObject1.toString();
		System.out.println(jsonFor1);
		//4.JSONObject to POJO
		System.out.println("++++++++4.jsonObject to  pojo++++++++++++++++++++++++++++++++++++++++");
		JsonForTest jsonForTest1 = (JsonForTest) JSONObject.toBean(jsonObject1, JsonForTest.class);
		System.out.println(jsonForTest1);
		
		//5.String to POJO
		System.out.println("++++++++5.String to  pojo++++++++++++++++++++++++++++++++++++++++");
//		JsonForTest jsonForTest2 = JSONObject.parseObject(jsonFor1, JsonForTest.class);
//		System.out.println(jsonForTest2);
		//6.String to JSONObject
		System.out.println("++++++++6.String to  JSONObject++++++++++++++++++++++++++++++++++++++++");
		JSONObject jsonObject2 = JSONObject.fromObject(jsonFor1);
		System.out.println(jsonObject2);
	}

}
