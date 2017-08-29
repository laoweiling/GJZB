package com.lnsf.service;

import java.util.List;

import com.lnsf.entity.Nav;

public interface NavService {
	//显示导航条,管理员
	public List<Nav> getAllList();
	//显示导航条,普通用户
	public List<Nav> getListByCommon();
}
