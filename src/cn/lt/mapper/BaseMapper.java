package cn.lt.mapper;

import java.util.List;

/**
 * Mapper基础操作公共接口
 * @author lt
 * @Date 2017年12月29日 上午10:43:25
 * @param <T>
 */
public interface BaseMapper<T> {
	
	int addEntity(T entity);
	
	int deleteEntity(int id);
	
	int updateEntity(T entity);
	
	T queryById(int id);
	
	List<T> queryAll();

}
