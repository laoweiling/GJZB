package com.lnsf.entity;

import java.util.List;

/**
 * 实现分页
 * @author 梁肖萍
 *
 * @param <T>
 */
public class Page<T> {
	private int start;
	private int end; 
	private int total; //返回总数
	private List<T> rows;//哪个对象使用分页
	
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
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
	
}
