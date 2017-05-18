package com.linkstec.redis;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {

	private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

	// private static String hostsAndPorts;

	private static String keyNameSpace;

	private static JedisPool jedisPool = null;

	private static Jedis jedisForGet = null;

	private static String hostAndPort;

	/**
	 * 获取Jedis实例,单服务器调用模式
	 * 
	 * @return
	 */
	public synchronized static Jedis getJedis() {
		if (null == jedisPool) {
			JedisPoolConfig config = new JedisPoolConfig();
			// 最大连接数
			config.setMaxTotal(100);
			// 最大空闲数
			config.setMaxIdle(40);
			// 最大等待时间
			config.setMaxWaitMillis(1000);
			// 验证
			config.setTestOnBorrow(true);
			String host = hostAndPort.split(":")[0];
			int port = Integer.valueOf(hostAndPort.split(":")[1]);
			jedisPool = new JedisPool(config, host, port, 10000);
		}
		int tryTimes = 3;
		while (tryTimes > 0) {
			try {
				tryTimes--;
				jedisForGet = jedisPool.getResource();
				if (jedisForGet != null) {
					break;
				}
			} catch (Exception e) {
				if (tryTimes > 0) {
					continue;
				}
				e.printStackTrace();
				throw new RuntimeException("redis没有连接,请检查redis集群");
			}
		}
		return jedisForGet;
	}

	/**
	 * 释放jedis资源
	 * 
	 * @param jedis
	 */
	public static void returnResource(final Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}

	@SuppressWarnings("rawtypes")
	public static void setConfig(Map cfg) {
		// hostsAndPorts = (String)cfg.get(MotConst.GCK_REDIS_CLUSTER);
		keyNameSpace = (String) cfg.get("keyNamespace");
		hostAndPort = (String) cfg.get("redisHostAndPortSingle");
	}

	// set key-value
	public static void set(String key, Object value) throws Exception {
		String redisKey = keyNameSpace + key;
		String strVal = null;
		Jedis jedis = getJedis();
		try {
			if (value != null) {
				if (!(value instanceof String)) {
					strVal = JSON.toJSONString(value);
				} else {
					strVal = (String) value;
				}
			}
			jedis.set(redisKey, strVal);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			returnResource(jedis);
		}
	}

	// set key-value and set key expire time
	public static void setExpire(String key, int expireTime, Object value) {
		String redisKey = keyNameSpace + key;
		String strVal = null;
		Jedis jedis = getJedis();
		try {
			if (value != null) {
				if (!(value instanceof String)) {
					strVal = JSON.toJSONString(value);
				} else {
					strVal = (String) value;
				}
			}
			jedis.setex(redisKey, expireTime, strVal);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			returnResource(jedis);
		}
	}

	// get value by key
	@SuppressWarnings("unchecked")
	public static <T> T get(String key, Class<T> clazz) throws Exception {
		String redisKey = keyNameSpace + key;
		T value = null;
		Jedis jedis = getJedis();
		try {
			String strVal = jedis.get(redisKey);
			if (strVal != null && !String.class.equals(clazz)) {
				value = (T) JSON.parseObject(strVal, clazz);
			} else {
				value = (T) strVal;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			returnResource(jedis);
		}
		return value;
	}

	// put a hashmap to redis
	public static void putHashMap(String key, Map<String, String> map) throws Exception {
		String redisKey = keyNameSpace + key;
		Jedis jedis = getJedis();
		try {
			jedis.hmset(redisKey, map);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			returnResource(jedis);
		}
	}

	// get hash map element
	public static String hget(String key, String hashKey) throws Exception {
		String redisKey = keyNameSpace + key;
		String result = null;
		Jedis jedis = getJedis();
		try {
			result = jedis.hget(redisKey, hashKey);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public static void hSet(String key, String hashKey, Object hashValue) throws Exception {
		String redisKey = keyNameSpace + key;
		String strVal = null;
		Jedis jedis = getJedis();
		try {
			if (hashValue != null) {
				if (!(hashValue instanceof String)) {
					strVal = JSON.toJSONString(hashValue);
				} else {
					strVal = (String) hashValue;
				}
			}
			jedis.hset(redisKey, hashKey, strVal);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			returnResource(jedis);
		}
	}

	public static boolean hContains(String key, String hashKey) throws Exception {
		String redisKey = keyNameSpace + key;
		boolean result = false;
		Jedis jedis = getJedis();
		try {
			result = jedis.hexists(redisKey, hashKey);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			returnResource(jedis);
		}

		return result;
	}

	public static Set<String> hKeys(String key) {
		String redisKey = keyNameSpace + key;
		Set<String> result = null;
		Jedis jedis = getJedis();
		try {
			result = jedis.hkeys(redisKey);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			returnResource(jedis);
		}

		return result;
	}

	public static void hDel(String key, String hashKey) throws Exception {
		String redisKey = keyNameSpace + key;
		Jedis jedis = getJedis();
		try {
			jedis.hdel(redisKey, hashKey);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			returnResource(jedis);
		}
	}

	public static void del(String key) {
		String redisKey = keyNameSpace + key;
		Jedis jedis = null;
		try {
			if (!StringUtils.isEmpty(key)) {
				jedis = getJedis();
				jedis.del(redisKey);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			returnResource(jedis);
		}
	}

	public static void del(Collection<String> keys) {
		Jedis jedis = null;
		try {
			if (null != keys && !keys.isEmpty()) {
				for (String key : keys) {
					jedis = getJedis();
					String redisKey = keyNameSpace + key;
					jedis.del(redisKey);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			returnResource(jedis);
		}
	}

	public static void delByPrefix(String keyPrefix) {
		String redisKey = keyNameSpace + keyPrefix;
		Jedis jedis = jedisPool.getResource();
		try {
			Set<String> keySet = jedis.keys(redisKey);
			if (null == keySet || keySet.isEmpty()) {
				for (String key : keySet) {
					jedis.del(key);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			returnResource(jedis);
		}
	}

	public static void addSet(String key, Object value) {
		String redisKey = keyNameSpace + key;
		String strVal = null;
		Jedis jedis = getJedis();
		try {
			if (value != null) {
				if (!(value instanceof String)) {
					strVal = JSON.toJSONString(value);
				} else {
					strVal = (String) value;
				}
			}
			jedis.sadd(redisKey, strVal);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			returnResource(jedis);
		}
	}

	public static boolean setContains(String key, Object value) {
		String redisKey = keyNameSpace + key;
		String strVal = null;
		boolean result = false;
		Jedis jedis = getJedis();
		try {
			if (value != null) {
				if (!(value instanceof String)) {
					strVal = JSON.toJSONString(value);
				} else {
					strVal = (String) value;
				}
			}
			result = jedis.sismember(redisKey, strVal);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			returnResource(jedis);
		}

		return result;
	}

	public static Set<String> sMembers(String key) {
		String redisKey = keyNameSpace + key;
		Set<String> result = null;
		Jedis jedis = getJedis();
		try {
			result = jedis.smembers(redisKey);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			returnResource(jedis);
		}

		return result;
	}

	public static Long incrBy(final String key, final long integer) {
		String redisKey = keyNameSpace + key;
		Long result;
		Jedis jedis = getJedis();
		try {
			result = jedis.incrBy(redisKey, integer);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	public static void addList(String key, Object value) {
		String redisKey = keyNameSpace + key;
		String strVal = null;
		Jedis jedis = getJedis();
		try {
			if (value != null) {
				if (!(value instanceof String)) {
					strVal = JSON.toJSONString(value);
				} else {
					strVal = (String) value;
				}
			}
			jedis.rpush(redisKey, strVal);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			returnResource(jedis);
		}
	}

	public static List<String> lrange(String key) {
		String redisKey = keyNameSpace + key;
		List<String> result = null;
		Jedis jedis = getJedis();
		try {
			result = jedis.lrange(redisKey, 0, -1);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			returnResource(jedis);
		}

		return result;
	}

	public static void expired(String key, int seconds) {
		String redisKey = keyNameSpace + key;
		Jedis jedis = getJedis();
		try {
			jedis.expire(redisKey, seconds);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			returnResource(jedis);
		}
	}

	public static boolean exists(String key) {
		String redisKey = keyNameSpace + key;
		boolean result = false;
		Jedis jedis = getJedis();
		try {
			result = jedis.exists(redisKey);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			returnResource(jedis);
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
			// ObjectMapper objectMapper = new ObjectMapper();
			// HashMap<String, String> testmap = new HashMap<String, String>();
			// testmap.put("test1", "test1");
			// testmap.put("test2", objectMapper.writeValueAsString(null));
//			hostAndPort = "10.189.110.225:19000";
			hostAndPort = "192.168.10.141:19000";
			RedisUtil.addSet("testSet", "1");
			RedisUtil.addSet("testSet", "2");
			RedisUtil.addSet("testSet", "3");
			System.out.println(RedisUtil.setContains("testSet", "3"));
			System.out.println(RedisUtil.setContains("testSet", "4"));
			System.out.println(RedisUtil.sMembers("testSet"));
			RedisUtil.del("testSet");
			for (int i = 0; i < 10000; i ++){
				RedisUtil.incrBy("incr", 1);
			}
			System.out.println(RedisUtil.get("incr", String.class));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
