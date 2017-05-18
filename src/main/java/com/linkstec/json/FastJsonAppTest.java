package com.linkstec.json;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * fastjson
 * @author liuyl
 *
 */
public class FastJsonAppTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JsonForTest jsonForTest = new JsonForTest();
		jsonForTest.setBusId("f  ff");
		JsonMsgContent JsonMsgContent = new JsonMsgContent();
		JsonMsgContent.setDdoubl(111.22);
		JsonMsgContent.setExchangeFree("测试");;
		String msgContent = JSONObject.toJSONString(JsonMsgContent, SerializerFeature.WriteNullStringAsEmpty);
		jsonForTest.setMsgContent(msgContent);
		
		Date date = new Date();
		jsonForTest.setDate(date);

		//1.POJO to jsonObject
		JSONObject jsonObject1 = (JSONObject) JSONObject.toJSON(jsonForTest);
		System.out.println("++++++++1.pojo to jsonObject++++++++++++++++++++++++++++++++++++++++");
		System.out.println(jsonObject1);
		System.out.println("++++++++2.pojo to  String++++++++++++++++++++++++++++++++++++++++");
		//2.POJO to String
		String jsonFor = JSONObject.toJSONString(jsonForTest, SerializerFeature.WriteNullStringAsEmpty);
		System.out.println(jsonFor);

		//3.JSONObject to String
		System.out.println("++++++++3.jsonObject to  String++++++++++++++++++++++++++++++++++++++++");
		System.out.println(jsonObject1.toJSONString());
		//4.JSONObject to POJO
		System.out.println("++++++++4.jsonObject to  pojo++++++++++++++++++++++++++++++++++++++++");
		JsonForTest jsonForTest1 = JSONObject.toJavaObject(jsonObject1, JsonForTest.class);
		System.out.println(jsonForTest1);
		
		//5.String to POJO
		System.out.println("++++++++5.String to  pojo++++++++++++++++++++++++++++++++++++++++");
		JsonForTest jsonForTest2 = JSONObject.parseObject(jsonFor, JsonForTest.class);
		System.out.println(jsonForTest2);
		//6.String to JSONObject
		System.out.println("++++++++6.String to  JSONObject++++++++++++++++++++++++++++++++++++++++");
		JSONObject jsonObject2 = JSONObject.parseObject(jsonFor);
		System.out.println(jsonObject2);
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
		String jsonString = JSONObject.toJSONString(jsonForTest, SerializerFeature.WriteNullStringAsEmpty);
		String jsonString2 = JSONObject.toJSONString(jsonString, SerializerFeature.WriteNullStringAsEmpty);
		String jsonString3 = JSONObject.toJSONString(jsonString2, SerializerFeature.WriteNullStringAsEmpty);
		System.out.println(jsonString);
		System.out.println(jsonString2);
		System.out.println(jsonString3);
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");

	}

}
