package cn.lt.util;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 使用Redis实现Mybatis的二级缓存
 * @author lt
 * @Date 2017年12月25日 下午3:46:03
 */
public class MyRedisCache2 implements Cache {
	
	 /**
	  * redis客户端对象
	  */
	 private Jedis redisClient = createReids();
	
	 protected static Jedis createReids(){  
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1");  
        return pool.getResource();  
     } 

	/**
     * The {@code ReadWriteLock}.
     */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * cacheName，cache唯一标识
     * 接口的全类名（namespace）
     */
    private final String id;

    /**
     * 初始化Cache
     * @param id
     */
    public MyRedisCache2(final String id) {
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
    	redisClient.flushDB();  
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
    	byte[] keyByte = redisClient.get(SerializeUtil.serialize(key.toString()));
    	if(keyByte == null || keyByte.length == 0){
    		return null;
    	}
    	Object value = SerializeUtil.unserialize(keyByte);  
    	
        return value; 
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
    	return Integer.valueOf(redisClient.dbSize().toString()); 
    }

    /**
     * 保存缓存对象Element
     */
    @Override
    public void putObject(Object key, Object value) {
        redisClient.set(SerializeUtil.serialize(key.toString()), SerializeUtil.serialize(value)); 
    }

    /**
     * 移除缓存对象Element，并返回缓存对象的值
     */
    @Override
    public Object removeObject(Object key) {
    	return redisClient.expire(SerializeUtil.serialize(key.toString()), 0);  
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
