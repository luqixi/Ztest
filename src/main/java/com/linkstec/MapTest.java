package com.linkstec;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("test", "fuck");
		map.put("test", "fuck");
		map.put("TEST", "fuck");
		System.out.println(map.size());
		System.out.println(map.get("test"));
		System.out.println(map.get("ddd"));
		System.out.println(map.toString());
		System.out.println("华丽的分割线----------------------------------");
		Map<String, Object> data = new HashMap<String, Object>();
		// 造数据cljg
		data.put("cljg", "8");
/*		// 造数据shjgfsy
		data.put("shjgfsy", "9");*/
		
		// 审核结果附属语
		Map<String, String> shjgfsyMap = new HashMap<String, String>();
		shjgfsyMap.put("1", "。");
		shjgfsyMap.put("9", "，请重新办理，详情登录涨乐财富通查看。");
		shjgfsyMap.put("7", "，请登录涨乐财富通重新上传影像后再提交！");
		if (null != shjgfsyMap.get((String) data.get("cljg"))) {
			data.put("shjgfsy", shjgfsyMap.get((String) data.get("cljg")));
		} else {
			data.put("shjgfsy", "其它");
		}
		// 处理结果
		Map<String, String> cljgMap = new HashMap<String, String>();
		cljgMap.put("1", "办理成功");
		cljgMap.put("9", "办理失败");
		cljgMap.put("7", "影像审核未通过");
		if (null != cljgMap.get((String) data.get("cljg"))) {
			data.put("cljg", cljgMap.get((String) data.get("cljg")));
		} else {
			data.put("cljg", "其它");
		}
		
		
		System.out.println(data.get("cljg"));
		System.out.println(data.get("shjgfsy"));
		
		System.out.println("华丽的分割线----------------------------------");
		Map<String, Object> nullmap = new HashMap<String, Object>();
		nullmap.put(null, null);
		nullmap.put(null, "haode");
		nullmap.put("1", null);
		nullmap.put("2", null);
		System.out.println(nullmap.size());
		System.out.println(nullmap.get(null));
		System.out.println(nullmap.get("1"));
		
	}

}
