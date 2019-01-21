package cn.lt.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.lt.model.StuScore;
import cn.lt.service.StuScoreService;

/**
 * 操作学生成绩信息的Controller
 * @author lt
 * @Date 2017年12月29日 上午10:04:00
 */
@Controller
@RequestMapping(value = "/score")
public class StuScoreController {
	
	@Autowired
	private StuScoreService stuScoreService;
	
	/**
	 * 查询学生成绩
	 * @return
	 */
	@RequestMapping(value = "/stu/{name}")
	public ModelAndView getStuScore(@PathVariable String name){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("score/stuScore");
		
		try {
			name = new String(name.getBytes("ISO8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<StuScore> stuScores = stuScoreService.queryScoreByName(name);
		mv.addObject("scores", stuScores);
		System.out.println(stuScores);
		
		return mv;
	}
	
	/**
	 * 查询课程成绩
	 * @return
	 */
	@RequestMapping(value = "/course/{course}")
	public ModelAndView getCourseScore(@PathVariable String course){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("score/stuScore");

		try {
			course = new String(course.getBytes("ISO8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<StuScore> stuScores = stuScoreService.queryScoreByCourse(course);
		mv.addObject("scores", stuScores);
		System.out.println(stuScores);
		
		return mv;
	}

}
