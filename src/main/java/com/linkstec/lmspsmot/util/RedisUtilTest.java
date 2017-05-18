package com.linkstec.lmspsmot.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtilTest {

	private static final Logger logger = LoggerFactory.getLogger(RedisUtilTest.class);
	
	public static JedisPool pool = null;
	
	private static String hostsAndPorts;
		
	
	private static JedisCluster jedisCluster = null;
	
	

	// 从连接池获取redis连接
	public static JedisCluster getJedisCluster() {
		if(jedisCluster == null)
		{
			JedisPoolConfig poolConfig = new JedisPoolConfig();
			poolConfig.setMaxTotal(40);
			poolConfig.setMaxIdle(40);
			poolConfig.setMinIdle(10);
			poolConfig.setMaxWaitMillis(15000);
			poolConfig.setTestOnBorrow(true);
			
			Set<HostAndPort> nodes = new HashSet<HostAndPort>();
			String[] hostAndPortArray = hostsAndPorts.split(",");
			for(String hostAndPort : hostAndPortArray)
			{
				String host = hostAndPort.split(":")[0];
				int port = Integer.valueOf(hostAndPort.split(":")[1]);
				nodes.add(new HostAndPort(host, port));
			}
			
			jedisCluster =  new JedisCluster(nodes);
		}
		
		return jedisCluster;
		
	}

	// set key-value
	public static void set(String key, Object value) throws Exception {
		
		String strVal = null;
		try {
			JedisCluster jedisCluster = getJedisCluster();
			if (value != null) {
				if (!(value instanceof String)) {
					strVal = JSON.toJSONString(value);
				} else {
					strVal = (String) value;
				}
			}
			jedisCluster.set(key, strVal);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	// set key-value and set key expire time
	public static void setExpire(String key, int expireTime, Object value) {
		String strVal = null;
		try {
			JedisCluster jedisCluster = getJedisCluster();
			if (value != null) {
				if (!(value instanceof String)) {
					strVal = JSON.toJSONString(value);
				} else {
					strVal = (String) value;
				}
			}
			jedisCluster.setex(key, expireTime, strVal);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	// get value by key
	@SuppressWarnings("unchecked")
	public static <T> T get(String key, Class<T> clazz) throws Exception {
		T value = null;
		try {
			JedisCluster jedisCluster = getJedisCluster();
			String strVal = jedisCluster.get(key);
			if (strVal != null && !String.class.equals(clazz)) {
				value = (T) JSON.parseObject(strVal, clazz);
			} else {
				value = (T) strVal;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return value;
	}

	// put a hashmap to redis
	public static void putHashMap(String key, Map<String, String> map)
			throws Exception {
		try {
			JedisCluster jedisCluster = getJedisCluster();
			jedisCluster.hmset(key, map);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	// get hash map element
	public static String hget(String key, String hashKey) throws Exception {
		String result = null;
		try {
			JedisCluster jedisCluster = getJedisCluster();
			result = jedisCluster.hget(key, hashKey);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	public static void hSet(String key, String hashKey, Object hashValue) throws Exception {
		String strVal = null;
		try {
			JedisCluster jedisCluster = getJedisCluster();
			if (hashValue != null) {
				if (!(hashValue instanceof String)) {
					strVal = JSON.toJSONString(hashValue);
				} else {
					strVal = (String) hashValue;
				}
			}
			jedisCluster.hset(key, hashKey, strVal);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
	
	public static boolean hContains(String key, String hashKey) throws Exception {
		boolean result = false;
		try {
			JedisCluster jedisCluster = getJedisCluster();
			result = jedisCluster.hexists(key, hashKey);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		return result;
	}
	public static Set<String> hKeys(String key) 
	{
		Set<String> result = null;
		try {
			JedisCluster jedisCluster = (JedisCluster) getJedisCluster();
			result = jedisCluster.hkeys(key);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		return result;
	}
	public static void hDel(String key, String hashKey) throws Exception {
		try {
			JedisCluster jedisCluster = getJedisCluster();
			jedisCluster.hdel(key, hashKey);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	public static void del(String key) {
		try {
			if (!StringUtils.isEmpty(key)) {
				JedisCluster jedisCluster = getJedisCluster();
				jedisCluster.del(key);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
	
	public static void del(Collection<String> keys) {
		try {
			if (null != keys && !keys.isEmpty()) {
				JedisCluster jedisCluster =  getJedisCluster();
				for (String key : keys){
					jedisCluster.del(key);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	public static void delByPrefix(String keyPrefix) {
		try {
			JedisCluster jedisCluster =  getJedisCluster();
			Map<String, JedisPool> jedisPoolMap = jedisCluster.getClusterNodes();
			for(JedisPool jedisPool : jedisPoolMap.values())
			{
				Jedis jedis = jedisPool.getResource();
				Set<String> keySet = jedis.keys(keyPrefix);
				if (null == keySet || keySet.isEmpty()){
					String[] keys = (String[])keySet.toArray(new String[keySet.size()]);
					jedis.del(keys);
					jedis.close();
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	public static void addSet(String key, Object value)
	{
		String strVal = null;
		try {
			JedisCluster jedisCluster = getJedisCluster();
			if (value != null) {
				if (!(value instanceof String)) {
					strVal = JSON.toJSONString(value);
				} else {
					strVal = (String) value;
				}
			}
			jedisCluster.sadd(key, strVal);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
	
	public static boolean setContains(String key, Object value)
	{
		String strVal = null;
		boolean result = false;
		try {
			JedisCluster jedisCluster = getJedisCluster();
			if (value != null) {
				if (!(value instanceof String)) {
					strVal = JSON.toJSONString(value);
				} else {
					strVal = (String) value;
				}
			}
			result = jedisCluster.sismember(key, strVal);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		return result;
	}
	
	public static Set<String> sMembers(String key)
	{
		Set<String> result = null;
		try {
			JedisCluster jedisCluster = getJedisCluster();
			result = jedisCluster.smembers(key);
		}catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		return result;
	}

	public static Long incrBy(final String key, final long integer)
	{
		Long result;
		try {
			JedisCluster jedisCluster = getJedisCluster();
			result =  jedisCluster.incrBy(key, integer);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	public static void addList(String key, Object value)
	{
		String strVal = null;
		try {
			JedisCluster jedisCluster = getJedisCluster();
			if (value != null) {
				if (!(value instanceof String)) {
					strVal = JSON.toJSONString(value);
				} else {
					strVal = (String) value;
				}
			}
			jedisCluster.rpush(key, strVal);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}
	
	public static List<String> lrange(String key)
	{
		List<String> result = null;
		try {
			JedisCluster jedisCluster = getJedisCluster();
			result = jedisCluster.lrange(key, 0, -1);
		}catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		try {
			// Set<String> keys = RedisUtil.getKeysByPrefix("w*");
			// for(String key : keys)
			// {
			// System.out.println(key);
			// }
			// RedisUtil.set("test", "Hello World!");
			// Long beginTime = System.currentTimeMillis();
			// for (int i = 0; i < 1000; i++) {
			// RedisUtil.get("test", String.class);
			// }
			// RedisUtil.del("test");
			// System.out.println("costs " + (System.currentTimeMillis() -
			// beginTime) + " ms.");
			// Map<String, String> map = new HashMap<String, String>();
			// map.put("test1", "test1");
			// map.put("test2", "test2");
			// RedisUtil.putHashMap("test", map);
			// beginTime = System.currentTimeMillis();
			// for (int i = 0; i < 1000; i++) {
			// RedisUtil.hget("test", "test1");
			// }
			// System.out.println("costs " + (System.currentTimeMillis() -
			// beginTime) + " ms.");
			// RedisUtil.del("test");
			// RedisUtil.bLPush("test123", "test1", 2);
			// RedisUtil.bLPush("test123", "test2", 2);
			// System.out.println("bRpop result:" +
			// RedisUtil.bRPop(String.class, "test123"));
			// RedisUtil.del("test123");
//			ObjectMapper objectMapper = new ObjectMapper();
//			HashMap<String, String> testmap = new HashMap<String, String>();
//			testmap.put("test1", "test1");
//			testmap.put("test2", objectMapper.writeValueAsString(null));
			
			hostsAndPorts = "192.168.70.188:8379,192.168.70.188:8380,192.168.70.188:8381,192.168.70.189:8379,192.168.70.189:8380,192.168.70.189:8381";
			/*RedisUtilTest.addSet("testSet", "1");
			RedisUtilTest.addSet("testSet", "2");
			RedisUtilTest.addSet("testSet", "3");
			System.out.println(RedisUtilTest.setContains("testSet", "3"));
			System.out.println(RedisUtilTest.setContains("testSet", "4"));
			System.out.println("set集合：");
			System.out.println(RedisUtilTest.sMembers("testSet"));
			RedisUtilTest.del("testSet");
			RedisUtilTest.addList("testList", "fuck1");
			RedisUtilTest.addList("testList", "fuck2");
			RedisUtilTest.addList("testList", "fuck3");
			System.out.println("list集合：");
			System.out.println(RedisUtilTest.lrange("testList"));
			RedisUtilTest.del("testList");*/
			
			System.out.println("-------------------------华丽的分割线----------------------");
			System.out.println("-------------------------华丽的分割线----------------------");
			
			Map<String, Object> custCache = new HashMap<String, Object>();
			custCache.put("branchCode", 123l);
			custCache.put("custLevel", "custLevel");
			custCache.put("brokId","brokId");
			custCache.put("custName", "custName");
			custCache.put("poro", null);
			custCache.put("sfgstyfw", (String)null);
			System.out.println(RedisUtilTest.get("jsonstr", String.class));
			RedisUtilTest.del("jsonstr");
			
			System.out.println("-------------------------华丽的分割线----------------------");
			JedisCluster jedisCluster =  getJedisCluster();
			Map<String, JedisPool> jedisPoolMap = jedisCluster.getClusterNodes();
			for(JedisPool jedisPool : jedisPoolMap.values())
			{
				Jedis jedis = jedisPool.getResource();
				Set<String> keySet = jedis.keys("CRM-MOT-" + "customerInfo_custNo_*");
				Set<String> keySet2 = jedis.keys("CRM-MOT-" + "customerInfo_brokId_*");
				
				if(!keySet.isEmpty())
				{
					System.out.println("size:" + keySet.size());
					//String[] keys = (String[])keySet.toArray(new String[keySet.size()]);
					/*for(String key : keySet){
						jedisCluster.del(key);
					}*/
				}
				if(!keySet2.isEmpty())
				{
//					System.out.println("size:" + keySet2.size());
					//String[] keys = (String[])keySet.toArray(new String[keySet.size()]);
					/*for(String key : keySet2){
						jedisCluster.del(key);
					}*/
				}
				jedis.close();
			}
			
			
			String sss = null;
			RedisUtilTest.del(sss);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
