package cn.lt.service;

import java.util.List;

import cn.lt.model.StuScore;

/**
 * 操作学生成绩信息的Service接口
 * @author lt
 * @Date 2017年12月21日 下午2:42:09
 */
public interface StuScoreService extends BaseService<StuScore>{

	List<StuScore> queryScoreByName(String name);
	
	List<StuScore> queryScoreByCourse(String course);

}
