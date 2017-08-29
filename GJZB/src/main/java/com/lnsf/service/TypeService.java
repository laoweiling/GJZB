package com.lnsf.service;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.lnsf.entity.Type;

/**
* @author 黄浩贡
* @version 创建时间：2017年7月27日22:51:56
* @introduction  service层
*/
public interface TypeService {
	//查找所有信息
	public List<Type> getTypes();
	//根据ID查找信息
	public Type getTypeById(Integer TYPEID);

}
