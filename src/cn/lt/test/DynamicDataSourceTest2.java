package cn.lt.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.lt.model.StuScore;
import cn.lt.model.User;
import cn.lt.service.StuScoreService;
import cn.lt.service.UserService;

/**
 * 测试基于AOP的动态切换数据源
 * @author lt
 * @Date 2017年12月28日 上午9:57:24
 */
@RunWith(SpringJUnit4ClassRunner.class)//使用spring测试框架
@ContextConfiguration("/spring-mybatis-dynamicDB.xml")//加载配置文件
public class DynamicDataSourceTest2 {
	@Autowired
	private UserService userService;
	@Autowired
	private StuScoreService stuScoreService;
	
	@Test
	public void TestAdd2(){
		User result = userService.queryById(2);
		System.out.println(result);
		
		List<StuScore> results = stuScoreService.queryScoreByName("李四");
		System.out.println(results);
	}
	
	@Test
	public void TestAdd(){
		List<StuScore> results = stuScoreService.queryScoreByName("李四");
		System.out.println("另外的访问：："+results);
	}
	
}
