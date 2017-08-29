package com.lnsf.entity;

public class BidCondition {

	/**
	 * 用户选择可投标项目的条件模糊查询的条件实体类
	 * @author 肖梦雅
	 */
	
	private Integer userId;
	private Integer typeId;  
	private Double minPrice;
	private Double maxPrice;
	private String projectName;
	private int start;    //start和end用于存储分页的信息，若使用page对象传过来，sql语句会报错
	private int end; 
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}
	public Double getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	@Override
	public String toString() {
		return "BidCondition [userId=" + userId + ", typeId=" + typeId + ", minPrice=" + minPrice + ", maxPrice="
				+ maxPrice + ", projectName=" + projectName + ", start=" + start + ", end=" + end + "]";
	}

	

	
	
	
	
}
