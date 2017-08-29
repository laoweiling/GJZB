package com.lnsf.dao;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.lnsf.entity.Nav;

public interface NavDao {
	//显示导航条,且只查询父节点,管理員
	@Select("select * from gjzb_nav where nid = 0")
	@Results(value={
			@Result(id=true,column="id",property="id"),
			@Result(column="text",property="text"),
			@Result(column="state",property="state"),
			@Result(column="url",property="url"),
			@Result(column="id",property="children",one=@One(select="getChildrensById"))
	})
	public List<Nav> getAllList();
	
	//显示导航条,且只查询父节点,普通用户
	@Select("select * from gjzb_nav where nid = 0 and text != '用户管理模块'")
	@Results(value={
			@Result(id=true,column="id",property="id"),
			@Result(column="text",property="text"),
			@Result(column="state",property="state"),
			@Result(column="url",property="url"),
			@Result(column="id",property="children",one=@One(select="getChildrensById"))
	})
	public List<Nav> getListByCommon();
	
	//查找孩子节点
	@Select("select * from gjzb_nav where nid = #{parentId}")
	@Results(value={
			@Result(id=true,column="id",property="id"),
			@Result(column="text",property="text"),
			@Result(column="state",property="state"),
			@Result(column="url",property="url"),
			@Result(column="id",property="children",one=@One(select="getChildrensById"))
	})
	public List<Nav> getChildrensById(int parentId);
	
}
