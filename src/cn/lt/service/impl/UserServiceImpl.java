package cn.lt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lt.annotation.DataSource;
import cn.lt.mapper.UserMapper;
import cn.lt.model.User;
import cn.lt.service.UserService;
import cn.lt.util.DBContextHolder;

/**
 * 操作用户信息的Service实现类
 * @author lt
 * @Date 2017年12月21日 下午2:42:35
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	
	@Override
	@DataSource(DBContextHolder.DATA_SOURCE_SLAVE)
	public int add(User user) {
		return userMapper.addEntity(user);
	}

	@Override
	@DataSource(DBContextHolder.DATA_SOURCE_SLAVE)
	public int delete(int id) {
		return userMapper.deleteEntity(id);
	}

	@Override
	@DataSource(DBContextHolder.DATA_SOURCE_SLAVE)
	public int update(User user) {
		return userMapper.updateEntity(user);
	}
	
	@Override
	@DataSource(DBContextHolder.DATA_SOURCE_SLAVE)
	public User queryById(int id){
		return userMapper.queryById(id);
	}

	@Override
	@DataSource(DBContextHolder.DATA_SOURCE_MASTER)
	public List<User> queryAll() {
		return userMapper.queryAll();
	}

}
