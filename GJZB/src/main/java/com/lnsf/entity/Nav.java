package com.lnsf.entity;

import java.util.List;

public class Nav {
	private int id;
	private String text;
	private String state;
	private String url;
	private List<Nav> children;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<Nav> getChildren() {
		return children;
	}
	public void setChildren(List<Nav> children) {
		this.children = children;
	}
	public Nav(int id, String text, String state, String url, List<Nav> children) {
		super();
		this.id = id;
		this.text = text;
		this.state = state;
		this.url = url;
		this.children = children;
	}
	public Nav() {
		super();
	}
	@Override
	public String toString() {
		return "Nav [id=" + id + ", text=" + text + ", state=" + state  + ", url=" + url
				+ ", children=" + children + "]";
	}
}
