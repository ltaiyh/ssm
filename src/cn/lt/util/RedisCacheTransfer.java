package cn.lt.util;

import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * 解决MyRedisCache.jedisConnectionFactory的静态注入
 * @author lt
 * @Date 2017年12月28日 下午2:24:35
 */
public class RedisCacheTransfer {

	/**
	 * 注入jedisConnectionFactory
	 * @param jedisConnectionFactory
	 */
	public void setJedisConnectionFactory(
			JedisConnectionFactory jedisConnectionFactory) {
		MyRedisCache.setJedisConnectionFactory(jedisConnectionFactory);
	}
	
}