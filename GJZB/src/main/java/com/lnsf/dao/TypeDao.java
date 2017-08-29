package com.lnsf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.lnsf.entity.Type;

/**
* @author 黄浩贡  黄卉
* @version 创建时间：2017年7月27日22:50:21
* @introduction  type表的增删改查
*/
public interface TypeDao {
	//查找所有信息
	@Select("select * from gjzb_type")
	public List<Type> getTypes();
	
	//根据ID查找信息
	@Select("select * from gjzb_type where TYPEID=#{TYPEID}")
	public Type getTypeById(Integer TYPEID);
	
	

}
