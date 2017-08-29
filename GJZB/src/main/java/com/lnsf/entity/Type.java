package com.lnsf.entity;

public class Type {
	private Integer typeId;      //主键ID
	private String projectType;  
	
	public Type() {
		super();
	}
	public Type(Integer typeId, String projectType) {
		super();
		this.typeId = typeId;
		this.projectType = projectType;
	}
	
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	@Override
	public String toString() {
		return "Type [typeId=" + typeId + ", projectType=" + projectType + "]";
	}

}
