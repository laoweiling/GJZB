package com.lnsf.entity;
/**
* @author 黄卉 
* @version 创建时间：2017年8月1日 上午8:45:58
* @introduction   读取文档实体类
*/
public class WordText {
	private String wordName;
	private String text;
	
	public String getWordName() {
		return wordName;
	}

	public void setWordName(String wordName) {
		this.wordName = wordName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "WordText [wordName=" + wordName + ", text=" + text + "]";
	}
	
}
