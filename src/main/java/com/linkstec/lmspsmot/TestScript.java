package com.linkstec.lmspsmot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.linkstec.lmspsmot.dto.EventParamDto;

public class TestScript {

	/**
	 * 
	 */

	public void onSource(String sourceId, Map<String, Object> data) {
		EventParamDto eventParamDto = null;
		List<EventParamDto> list = new ArrayList<EventParamDto>();
//		eventParamDto = new EventParamDto(); //
		for (int i = 0; i < 10; i++){
			eventParamDto = new EventParamDto();
			eventParamDto.setCustomerId("好的" + i);
			list.add(eventParamDto);
		}
		for (int i = 0; i < list.size(); i ++){
			
			System.out.println(list.get(i).getCustomerId());
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TestScript().onSource(null, null);

	}

}
