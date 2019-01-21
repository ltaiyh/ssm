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

@RunWith(SpringJUnit4ClassRunner.class)//使用spring测试框架
@ContextConfiguration("/spring-mybatis.xml")//加载配置文件
public class RedisTest {
	@Autowired
	private StuScoreMapper stuScoreMapper;
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void TestAdd2(){
		List<StuScore> results = stuScoreMapper.queryScoreByName("李四");
		System.out.println(results);
		
		results = stuScoreMapper.queryScoreByName("李四");
		System.out.println("再次访问----------：："+results);
		
	}
	
	@Test
	public void TestAdd(){
		List<StuScore> results = stuScoreMapper.queryScoreByName("李四");
		System.out.println("另外的访问：："+results);
		
		System.out.println("---------------");
		//userMapper.addUser(new User("redis", 88));
		User user = userMapper.queryById(2);
		System.out.println(user);
		
		List<User> users = userMapper.queryAll();
		System.out.println(users);
	}
	
}
