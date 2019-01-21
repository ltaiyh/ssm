package cn.lt.model;

import java.io.Serializable;

/**
 * 学生成绩实体类
 * @author lt
 * @Date 2017年12月22日 上午8:55:15
 */
public class StuScore implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String course;
	private String score;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "StuScore {id=" + id + ", name=" + name + ", course=" + course
				+ ", score=" + score + "}";
	}

}
