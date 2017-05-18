package com.linkstec;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SetTest {
	
	private static final Logger logger = LoggerFactory.getLogger(SetTest.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Set<String> senderCustSet = new HashSet<String>();
//		Set<String> senderCustSet2 = new HashSet<String>();
		Set<String> senderCustSet2 = null;
//		Set<String> senderCustSet = null;
		senderCustSet.add("hello");
		if (null != senderCustSet && senderCustSet.size() != 0) {
			logger.info("不为null，大小也大于0");
		} else {
			logger.info("为null，或者大小为0");
		}
		System.out.println("+++++++++++++++++++++++++++++++++++++++");

		senderCustSet.add("hell2");
		
		System.out.println(senderCustSet.toString());
		System.out.println(StringUtils.join(senderCustSet,","));
		System.out.println(StringUtils.join(senderCustSet,','));
		System.out.println(StringUtils.join(senderCustSet2,","));
	}
}