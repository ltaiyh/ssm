package cn.lt.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.lt.mapper.UserMapper;
import cn.lt.model.User;

@RunWith(SpringJUnit4ClassRunner.class)//使用spring测试框架
@ContextConfiguration("/spring-mybatis.xml")//加载配置文件
public class IntegrationTest {
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void TestAdd2(){
		List<User> result = userMapper.queryAll();
		System.out.println(result);
	}
	
}
