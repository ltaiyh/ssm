/*package cn.lt.mapper.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import cn.lt.mapper.UserMapper;
import cn.lt.model.User;

*//**
 * 操作用户信息的Mapper实现类，可以不写
 * @author lt
 * @Date 2017年12月21日 下午2:39:46
 *//*
@Repository
public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper {
	
	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteUser(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User queryById(int id) {
		System.out.println("用户查询Mapper");
		return this.getSqlSession().selectOne("cn.lt.mapper.UserMapper.queryById", 2);
	}

	@Override
	public List<User> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
*/