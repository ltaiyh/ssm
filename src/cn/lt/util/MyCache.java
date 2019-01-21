package cn.lt.util;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;

import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.CacheException;

/**
 * 自定义Mybatis二级缓存
 * @author lt
 * @Date 2017年12月22日 下午5:14:29
 */
public class MyCache implements Cache {

    /**
     * 以默认配置创建一个CacheManager单例  
     */
    private static final CacheManager CACHE_MANAGER = CacheManager.create();

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
     * 初始化Cache，添加到cachemanager中
     * @param id
     */
    public MyCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
        if (!CACHE_MANAGER.cacheExists(this.id)) {
            CACHE_MANAGER.addCache(this.id);
        }
    }

    /**
     * 清空缓存
     */
    @Override
    public void clear() {
        this.getCache().removeAll();
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
        try {
            Element cachedElement = this.getCache().get(key.hashCode());
            if (cachedElement == null) {
                return null;
            }
            return cachedElement.getObjectValue();
        } catch (Throwable t) {
            throw new CacheException(t);
        }
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
        try {
            return this.getCache().getSize();
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    /**
     * 保存缓存对象Element
     */
    @Override
    public void putObject(Object key, Object value) {
        try {
            this.getCache().put(new Element(key.hashCode(), value));
            this.getCache().flush();
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    /**
     * 移除缓存对象Element，并返回缓存对象的值
     */
    @Override
    public Object removeObject(Object key) {
        try {
            Object obj = this.getObject(key);
            this.getCache().remove(key.hashCode());
            return obj;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    /**
     * 根据cacheName获取对应的Cache
     */
    private Ehcache getCache() {
        return CACHE_MANAGER.getCache(this.id);
    }

    /**
     * 获取配置文件ehcache.xml中的配置信息
     */
    private CacheConfiguration getCacheConfiguration() {
        return this.getCache().getCacheConfiguration();
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
        return "EHCache {"
                + this.id
                + "}";
    }

    
    // 动态设置缓存对象的属性
    public void setTimeToLiveSeconds(long timeToLiveSeconds) {
    	System.out.println("设置timeToLiveSeconds");
        this.getCacheConfiguration().setTimeToLiveSeconds(timeToLiveSeconds);
    }

}
