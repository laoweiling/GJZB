package com.lnsf.entity;

/**
* @author 劳伟玲 
* @version 创建时间：2017年7月26日19:13:22
* @introduction  创建实体类Win
*/
public class Win {

	private Integer winId;
	private User user;
	private Project project;
	public Integer getWinId() {
		return winId;
	}
	public void setWinId(Integer winId) {
		this.winId = winId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Win() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Win(Integer winId, User user, Project project) {
		super();
		this.winId = winId;
		this.user = user;
		this.project = project;
	}
	@Override
	public String toString() {
		return "Win [winId=" + winId + ", user=" + user + "]";
	}
	

}
