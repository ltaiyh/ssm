package cn.lt.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.lt.mapper.StuScoreMapper;
import cn.lt.mapper.UserMapper;
import cn.lt.model.StuScore;
import cn.lt.model.User;
import cn.lt.util.DBContextHolder;

/**
 * 测试手动切换数据源
 * @author lt
 * @Date 2017年12月28日 上午9:56:54
 */
@RunWith(SpringJUnit4ClassRunner.class)//使用spring测试框架
@ContextConfiguration("/spring-mybatis-dynamicDB.xml")//加载配置文件
public class DynamicDataSourceTest {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private StuScoreMapper stuScoreMapper;
	
	@Test
	public void TestAdd2(){
		DBContextHolder.setDBType(DBContextHolder.DATA_SOURCE_SLAVE);
		
		User result = userMapper.queryById(2);
		System.out.println(result);
		
		System.out.println("切换数据源————————————————————");
		DBContextHolder.setDBType(DBContextHolder.DATA_SOURCE_MASTER);
		System.out.println(DBContextHolder.getDBType());
		
		List<StuScore> results = stuScoreMapper.queryScoreByName("李四");
		System.out.println(results);
		
		results = stuScoreMapper.queryScoreByName("李四");
		System.out.println("二次访问----------：："+results);
	}
	
	@Test
	public void TestAdd(){
		List<StuScore> results = stuScoreMapper.queryScoreByName("李四");
		System.out.println("二次访问：："+results);
	}
	
}
