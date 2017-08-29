package com.lnsf.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
* @author 黄卉 伟玲修改
* @version 创建时间：2017年7月26日 下午7:14:10
* @introduction  合同、项目的实体类
*/
public class Project {

	private Integer  projectId;
	private String projectName;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date releaseTime;//不可以改变的
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date lastTime;
	private String projectcontent;
	private Double price;   //改为对象类型 伟玲
	private Type type;
	private User user;
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Date getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public String getProjectcontent() {
		return projectcontent;
	}
	public void setProjectcontent(String projectcontent) {
		this.projectcontent = projectcontent;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Project(Integer projectId, String projectName, Date releaseTime, Date lastTime, String projectcontent,
			Double price, Type type, User user) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.releaseTime = releaseTime;
		this.lastTime = lastTime;
		this.projectcontent = projectcontent;
		this.price = price;
		this.type = type;
		this.user = user;
	}
	
	public Project(Integer projectId) {
		super();
		this.projectId = projectId;
	}
	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + ", releaseTime=" + releaseTime
				+ ", lastTime=" + lastTime + ", projectcontent=" + projectcontent + ", price=" + price + ", type="
				+ type + ", user=" + user + "]";
	}
	
	
	
}
