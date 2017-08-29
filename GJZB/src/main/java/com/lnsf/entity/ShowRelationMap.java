package com.lnsf.entity;

import java.util.List;

public class ShowRelationMap {
	private Project project;
	private List<User> listUser;
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public List<User> getListUser() {
		return listUser;
	}
	public void setListUser(List<User> listUser) {
		this.listUser = listUser;
	}
	
	@Override
	public String toString() {
		return "ShowRelationMap [project=" + project + ", listUser=" + listUser + "]";
	}
	public ShowRelationMap(Project project, List<User> listUser) {
		super();
		this.project = project;
		this.listUser = listUser;
	}
	public ShowRelationMap() {
		super();
		
	}
	

}
