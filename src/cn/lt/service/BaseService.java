package cn.lt.service;

import java.util.List;

/**
 * Service基础操作公共接口
 * @author lt
 * @Date 2017年12月29日 上午10:56:40
 * @param <T>
 */
public interface BaseService<T> {
	
	int add(T entity);
	
	int delete(int id);
	
	int update(T entity);
	
	T queryById(int id);
	
	List<T> queryAll();

}
