package com.linkstec.json;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * fastjson
 * 控制台打印json数据
 * @author liuyl
 *
 */
public class ConsoleFastJsonTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for(int i = 501; i < 1001; i++){
			Map<String, String> map = new HashMap<String, String>();
//			map.put("STOCK_CODE", "600716");
			map.put("STOCK_CODE", "111" + i);
			map.put("FSRQ", "2017-01-23");
			map.put("ANN_TITLE", "");
			map.put("ANN_CONTENT", i + "凤凰股份预计2016年年度归属于上市公司股东的净利润为4,900万元左右，同比实现扭亏为盈。");
			map.put("FILE_LINK", "");
			map.put("GPMC", "凤凰股份");
			
			
			String jsonFor = JSONObject.toJSONString(map, SerializerFeature.WriteNullStringAsEmpty);
			System.out.println(jsonFor);
		}

	}

}