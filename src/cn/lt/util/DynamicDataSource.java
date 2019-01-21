package cn.lt.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源切换
 * 选择数据源顺序时先根据determineCurrentLookupKey方法返回的值到targetDataSources中去找，
 * 若能找到则返回对应的数据源，若找不到返回默认的数据源defaultTargetDataSource
 * @author lt
 * @Date 2017年12月21日 下午4:53:54
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	/**
	 * 选择具体使用targetDataSources中的哪一个数据源
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		System.out.println("DynamicDataSource::"+DBContextHolder.getDBType());
		return DBContextHolder.getDBType();  
	}

}
