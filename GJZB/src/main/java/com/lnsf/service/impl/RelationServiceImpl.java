package com.lnsf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lnsf.dao.ProjectDao;
import com.lnsf.dao.RelationDao;
import com.lnsf.dao.UserDao;
import com.lnsf.entity.Page;
import com.lnsf.entity.Project;
import com.lnsf.entity.Relation;
import com.lnsf.entity.User;
import com.lnsf.service.RelationService;

@Service("relationService")
public class RelationServiceImpl implements RelationService {
	@Autowired
	ProjectDao projectDao;
	@Autowired
	RelationDao relationDao;

	@Override
	public int delRelationByPidAndUseridService(Project project) {
		return relationDao.delRelationByPidAndUserid(project);
	}

	@Override
	public List<Relation> getAllRelation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Relation getRelationById(Integer relationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteRelationById(Integer relationId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteRelationByUserId(Integer FKPUTID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteRelationByProjectId(Integer FKPROJECTID) {

		return 0;
	}

	@Override
	public int updateRelationById(Relation relation) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertRelation(Relation relation) {
		// TODO Auto-generated method stub
		return relationDao.insertRelation(relation);
	}

	@Override
	public List<Relation> getRelationByuserIdAndProjectId(Integer FKPUTID, Integer FKPROJECTID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Relation> getAllRelationsByUserId(Integer userId) {

		return relationDao.getRelationByUserId(userId);
	}

	@Override
	public int getCountTotalNum(Integer userId) {
		return relationDao.getTotalNum(userId);
	}

	@Override
	public List<Relation> getAllProjectsByUserIdPage(Page<Project> project_page, Integer FKPUTID) {
		// return relationDao.getAllProjectsByUserIdFromRela(FKPUTID);
		return null;
	}

	@Override
	public int getRelationCount() {
		return relationDao.getRelationCount();
	}

}
