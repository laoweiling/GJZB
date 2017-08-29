package com.lnsf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lnsf.dao.ProjectDao;
import com.lnsf.dao.RelationDao;
import com.lnsf.dao.TypeDao;
import com.lnsf.dao.WinDao;
import com.lnsf.entity.Project;
import com.lnsf.entity.Type;
import com.lnsf.service.TypeService;

/**
 * @author黄浩贡
 * @version 创建时间：2017年7月28日21:23:30
 * @introduction 创建type的增删改查操作
 *
 */
@Service("typeServiceImpl")
public class TypeServiceImpl implements TypeService {
	@Autowired
	TypeDao  typeDao;
	@Autowired
	WinDao winDao;
	@Autowired
	RelationDao relationDao;
	@Autowired
	ProjectDao projectDao;
	
	@Override
	public List<Type> getTypes() {
		// TODO Auto-generated method stub
		return typeDao.getTypes();
	}
	
	@Override
	public Type getTypeById(Integer TYPEID){
		return typeDao.getTypeById(TYPEID);
	}
	

}
