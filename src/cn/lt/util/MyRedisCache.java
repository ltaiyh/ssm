package cn.lt.util;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * 使用Redis实现Mybatis的二级缓存
 * @author lt
 * @Date 2017年12月25日 下午3:46:03
 */
public class MyRedisCache implements Cache {
	
	/**
	 * redis连接工厂
	 */
	private static JedisConnectionFactory jedisConnectionFactory;
	
	public static void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
	    MyRedisCache.jedisConnectionFactory = jedisConnectionFactory;
	}
	
	/**
     * The {@code ReadWriteLock}.
     */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    
    /**
     * 借用spring_data_redis.jar中的JdkSerializationRedisSerializer.class序列化类
     */
 	private final RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer(); 

    /**
     * cacheName，cache唯一标识
     * 接口的全类名（namespace）
     */
    private final String id;

    /**
     * 初始化Cache
     * @param id
     */
    public MyRedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }

    /**
     * 清空缓存
     */
    @Override
    public void clear() {
    	RedisConnection connection = null;
        try {
            connection = jedisConnectionFactory.getConnection(); //连接清除数据
            connection.flushDb();
            connection.flushAll();
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * 获取cacheName
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * 获取缓存对象的值
     */
    @Override
    public Object getObject(Object key) {
    	Object result = null;
    	RedisConnection connection = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			result = serializer.deserialize(connection.get(serializer
					.serialize(key))); // 利用其反序列化方法获取值
		} catch (JedisConnectionException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReadWriteLock getReadWriteLock() {
    	return this.readWriteLock;
    }

    /**
     * 获取缓存对象Element的数量
     */
    @Override
    public int getSize() {
    	int result = 0;
        RedisConnection connection = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			result = Integer.valueOf(connection.dbSize().toString());
		} catch (JedisConnectionException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
        return result;
    }

    /**
     * 保存缓存对象Element
     */
    @Override
    public void putObject(Object key, Object value) {
    	RedisConnection connection = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			connection.set(serializer.serialize(key),
					serializer.serialize(value));
		} catch (JedisConnectionException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
    }

    /**
     * 移除缓存对象Element，并返回缓存对象的值
     */
    @Override
    public Object removeObject(Object key) {
    	RedisConnection connection = null;
        Object result = null;
        try {
            connection = jedisConnectionFactory.getConnection();
            result =connection.expire(serializer.serialize(key), 0);
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Cache)) {
            return false;
        }

        Cache otherCache = (Cache) obj;
        return this.id.equals(otherCache.getId());
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public String toString() {
        return "RedisCache {"
                + this.id
                + "}";
    }
    
}
