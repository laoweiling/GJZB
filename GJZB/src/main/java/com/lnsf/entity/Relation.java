package com.lnsf.entity;

/**
* @author 劳伟玲 
* @version 创建时间：2017年7月26日19:13:22
* @introduction  创建实体类Relation
*/
public class Relation {

	private Integer relationId;
	private User user;
	private Project project;
	public Integer getRelationId() {
		return relationId;
	}
	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
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
	public Relation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Relation(Integer relationId, User user, Project project) {
		super();
		this.relationId = relationId;
		this.user = user;
		this.project = project;
	}
	@Override
	public String toString() {
		return "Relation [relationId=" + relationId + ", user=" + user + "]";
	}
	

}
