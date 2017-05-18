package com.linkstec.json;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * fastjson
 * @author liuyl
 *
 */
public class FastJsonAppTest2 {

	private static final Logger logger = LoggerFactory.getLogger(FastJsonAppTest2.class);
	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		JsonForTest jsonForTest = new JsonForTest();
		jsonForTest.setBusId("f  ff");
		jsonForTest.setDdoubl(222.22);
		JsonMsgContent JsonMsgContent = new JsonMsgContent();
		JsonMsgContent.setDdoubl(111.22);
		JsonMsgContent.setExchangeFree("测试");;
		String msgContent = JSONObject.toJSONString(JsonMsgContent, SerializerFeature.WriteNullStringAsEmpty);
		jsonForTest.setMsgContent(msgContent);
		
		Date date = new Date();
		jsonForTest.setDate(date);

		String jsonFor = JSONObject.toJSONString(jsonForTest, SerializerFeature.WriteNullStringAsEmpty);
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
		Map<String, Object> receiveMap = new HashMap<String, Object>();
		receiveMap = JSON.parseObject(jsonFor, Map.class);
		System.out.println(jsonFor);
		System.out.println(receiveMap);
		BigDecimal doubl11 = (BigDecimal)(receiveMap.get("doubl"));
		logger.info(doubl11.toString());
		
//		Double doubl = (Double)(receiveMap.get("doubl"));
		
	}
}