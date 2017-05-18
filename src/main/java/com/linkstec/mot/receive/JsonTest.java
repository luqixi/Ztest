package com.linkstec.mot.receive;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.linkstec.mot.bean.msg.EventParamDto;

public class JsonTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<EventParamDto> list = new ArrayList<EventParamDto>();
		EventParamDto eventParamDto = new EventParamDto();
		
		eventParamDto.setEventId(111112l);// 事件ID
		eventParamDto.setCustomerId("111"); //系统客户号
		eventParamDto.setAppendInformation("ddd"); // 附加信息
		eventParamDto.setEventFlowId(1l);
		eventParamDto.setBranchId("branchId");
		eventParamDto.setEventDesc("");
		eventParamDto.setBizDate(Long.valueOf(new SimpleDateFormat("yyyyMMdd").format( new Date())));
		System.out.println(new Date().getTime());
		System.out.println("-----------------------------------------------------------------");
		eventParamDto.setEventTrgTime(new Date());
		eventParamDto.setDealDeadline(new Date());
			eventParamDto.setBranchCode(111l);
		eventParamDto.setCustLevel("custLevel");
		eventParamDto.setBrokId("brokId");
		eventParamDto.setCustName("custName");
		eventParamDto.setpOrO("poro");
		eventParamDto.setSfgstyfw("sfgstyfw");
		eventParamDto.setRequestId(0l);
		list.add(eventParamDto);
		
		
		
		String receiveStr = JSONObject.toJSONString(list);
		System.out.println(receiveStr);
		JSONArray eventDtos = JSONArray.parseArray(receiveStr);
		
		for (int i = 0; i < eventDtos.size(); i++) {
			EventParamDto item = JSON.toJavaObject(
					eventDtos.getJSONObject(i), EventParamDto.class);
			System.out.println(item);
			System.out.println(item.getDealDeadline());
			System.out.println(item.getBranchCode());
		}

	}

}
