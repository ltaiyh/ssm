package cn.lt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lt.annotation.DataSource;
import cn.lt.mapper.StuScoreMapper;
import cn.lt.model.StuScore;
import cn.lt.service.StuScoreService;
import cn.lt.util.DBContextHolder;

/**
 * 操作学生成绩信息的Service实现类
 * @author lt
 * @Date 2017年12月21日 下午2:42:35
 */
@Service
public class StuScoreServiceImpl implements StuScoreService {
	@Autowired
	private StuScoreMapper stuScoreMapper;

	@Override
	@DataSource(DBContextHolder.DATA_SOURCE_MASTER)
	public int add(StuScore entity) {
		return stuScoreMapper.addEntity(entity);
	}

	@Override
	@DataSource(DBContextHolder.DATA_SOURCE_MASTER)
	public int delete(int id) {
		return stuScoreMapper.deleteEntity(id);
	}

	@Override
	@DataSource(DBContextHolder.DATA_SOURCE_MASTER)
	public int update(StuScore entity) {
		return stuScoreMapper.updateEntity(entity);
	}

	@Override
	@DataSource(DBContextHolder.DATA_SOURCE_MASTER)
	public StuScore queryById(int id) {
		return stuScoreMapper.queryById(id);
	}

	@Override
	@DataSource(DBContextHolder.DATA_SOURCE_MASTER)
	public List<StuScore> queryAll() {
		return stuScoreMapper.queryAll();
	}

	@Override
	@DataSource(DBContextHolder.DATA_SOURCE_MASTER)
	public List<StuScore> queryScoreByCourse(String course) {
		return stuScoreMapper.queryScoreByCourse(course);
	}
	
	@Override
	@DataSource(DBContextHolder.DATA_SOURCE_MASTER)
	public List<StuScore> queryScoreByName(String name) {
		return stuScoreMapper.queryScoreByName(name);
	}
	
}
