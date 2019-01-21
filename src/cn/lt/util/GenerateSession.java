package cn.lt.util;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 获取SqlSession的工具类
 * @author lt
 * @Date 2017年12月21日 下午2:43:37
 */
public class GenerateSession {
	
	/**
	 * 获取SqlSession实例
	 * @return
	 */
	public static SqlSession getSession(){
		//加载配置文件
		InputStream inputStream = GenerateSession.class.getClassLoader().getResourceAsStream("mybatis.xml");
		
		//创建sqlSessionFactory
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		
		//创建sqlSession
		SqlSession sqlSession = factory.openSession(true);
		
		return sqlSession;
	}

}
