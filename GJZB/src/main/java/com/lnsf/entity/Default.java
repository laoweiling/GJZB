package com.lnsf.entity;
/**
 * 首页要展示的信息
 * @author 梁肖萍
 *
 */

import java.util.List;
import java.util.Map;

public class Default {
	private Map<String, Integer> map; //图表
	private List<Project> project; //最新发布的项目
	private List<Project> hot_pro; //热门的项目
	private List<User> hot_user; //大神级别的人物
	
	
	public List<User> getHot_user() {
		return hot_user;
	}
	public void setHot_user(List<User> hot_user) {
		this.hot_user = hot_user;
	}
	public List<Project> getHot_pro() {
		return hot_pro;
	}
	public void setHot_pro(List<Project> hot_pro) {
		this.hot_pro = hot_pro;
	}
	public Map<String, Integer> getMap() {
		return map;
	}
	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}
	public List<Project> getProject() {
		return project;
	}
	public void setProject(List<Project> project) {
		this.project = project;
	}
	
	

}
