package cn.lt.mapper;

import java.util.List;

import cn.lt.model.StuScore;

/**
 * 操作学生成绩信息的Mapper接口
 * @author lt
 * @Date 2017年12月22日 上午8:58:07
 */
public interface StuScoreMapper extends BaseMapper<StuScore>{
	
	List<StuScore> queryScoreByName(String name);
	
	List<StuScore> queryScoreByCourse(String course);

}
