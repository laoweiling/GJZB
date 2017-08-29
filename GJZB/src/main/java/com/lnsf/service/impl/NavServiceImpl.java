package com.lnsf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lnsf.dao.NavDao;
import com.lnsf.entity.Nav;
import com.lnsf.service.NavService;

@Service("navService")
public class NavServiceImpl implements NavService {
	@Autowired
	private NavDao navDao;

	/**
	 * 显示导航条
	 */
	@Override
	public List<Nav> getAllList() {
		return navDao.getAllList();
	}

	@Override
	public List<Nav> getListByCommon() {
		return navDao.getListByCommon();
	}

}
