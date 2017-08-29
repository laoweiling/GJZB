package com.lnsf.entity;

/**
 * 包装项目和是否中标的标识的实体类
 * 投标人的已投标信息的分页（显示项目信息以及对应的中标与否信息）
 * @author 肖梦雅
 *
 */

public class Judge {
	private Project project;
	private Integer winFlag;
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Integer getWinFlag() {
		return winFlag;
	}
	public void setWinFlag(Integer winFlag) {
		this.winFlag = winFlag;
	}
	@Override
	public String toString() {
		return "Judge [project=" + project + ", winFlag=" + winFlag + "]";
	}
	
}
