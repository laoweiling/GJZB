package com.lnsf.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lnsf.dao.ProjectDao;
import com.lnsf.dao.RelationDao;
import com.lnsf.dao.UserDao;
import com.lnsf.dao.WinDao;
import com.lnsf.entity.Project;
import com.lnsf.entity.Relation;
import com.lnsf.entity.ShowRelationMap;
import com.lnsf.entity.User;
import com.lnsf.entity.Win;
import com.lnsf.service.WinService;

/**
 * @author 黄卉
 * @version 创建时间：2017年7月28日 上午11:56:16
 * @introduction
 */
@Service("winServiceImpl")
public class WinServiceImpl implements WinService {
	@Autowired
	WinDao  winDao;
	@Autowired
	RelationDao  relationDao;
	@Autowired
	private UserDao userService;
	@Autowired
	private ProjectDao projectService;

	@Override
	public List<Win> getAllWin() {
		// 获取所有信息
		return winDao.getAllWin();
	}

	// 根据表ID查找
	@Override
	public Win getWinByWinId(Integer winId) {
		return winDao.getWinByWinId(winId);
	}

	// 根据项目ID查找中标信息
	@Override
	public Win getWinByWinByFKprojectId(Integer fkprojectId) {
		return winDao.getWinByWinByFKprojectId(fkprojectId);
	}

	// 根据用户ID查找中标信息
	@Override
	public Win getWinByWinByFKUserId(Integer userId) {
		return winDao.getWinByWinByFKUserId(userId);
	}

	/*
	 * //插入信息
	 * 
	 * @Override public Integer addWin(Win win){ //Integer
	 * userId=win.getUser().getUserId(); //Integer
	 * projectId=win.getProject().getProjectId();
	 * //System.out.println("userId:"+userId);
	 * //System.out.println("projectId:"+projectId); // return
	 * winDao.addWin(userId,projectId); return winDao.addWin(win); }
	 */
	// 根据主键ID删除信息
	@Override
	public Integer deleteWinById(Integer WINID) {
		return winDao.deleteWinById(WINID);
	}

	// 根据用户ID删除信息
	@Override
	public Integer deleteWinByUserId(Integer UserId) {
		return winDao.deleteWinByUserId(UserId);
	}

	// 根据项目ID删除信息
	@Override
	public Integer deleteWinByProjectId(Integer ProjectId) {
		return winDao.deleteWinByProjectId(ProjectId);
	}

	// 查询win表中是否有重复数据
	@Override
	public List<Win> getWinByByUserIdAndProjectId(Integer FKGETID, Integer FKPROJECTID) {
		// TODO Auto-generated method stub
		return winDao.getWinByByUserIdAndProjectId(FKGETID, FKPROJECTID);
	}

	/**
	 * @author 劳伟玲
	 * @version 创建时间：2017年7月30日09:10:02
	 * @introduction
	 */
	// 插入信息
	@Override
	public Integer addWin(Win win) {

		Integer userId = win.getUser().getUserId();
		Integer projectId = win.getProject().getProjectId();
		// 判断有没有重复的数据
		List<Win> haswin = winDao.getWinByByUserIdAndProjectId(userId, projectId);
		if (haswin.isEmpty()) {
			// 考虑到有没有投标的这个人
			User user = userService.getUserById(userId);
			System.out.println(user);
			// 有没有投标的这个项目
			Project project = projectService.getProjectById(projectId);
			System.out.println(project);
			Integer userIdInProject = project.getUser().getUserId();
			System.out.println(userIdInProject);
			// isEqual为true时表明投标的项目是这个人所招标的，不能自己投自己项目
			boolean isEqual = (userId == userIdInProject);
			System.out.println(isEqual);

			if (!user.equals("") && !project.equals("") && !isEqual) {
				System.out.println("插入投标表成功");
				//win插入数据
				winDao.addWin(win);
				//需要删除投标表里面的数据
				return relationDao.deleteRelationByProjectId(projectId);
			} else {
				System.out.println("插入投标表失败");
				return 0;
			}
		} else {
			System.out.println("插入投标表失败");
			return 0;
		}

	}

	// 更新信息
	@Override
	public Integer updateWinByProjectId(Win win) {
		Integer userId = win.getUser().getUserId();
		Integer projectId = win.getProject().getProjectId();
		// 判断有没有重复的数据
		List<Win> haswin = winDao.getWinByByUserIdAndProjectId(userId, projectId);
		if (haswin.equals("")) {
			// 考虑到有没有中标的这个人
			User user = userService.getUserById(userId);
			System.out.println(user);
			// 有没有中标的这个项目
			Project project = projectService.getProjectById(projectId);
			System.out.println(project);
			Integer userIdInProject = project.getUser().getUserId();
			System.out.println(userIdInProject);
			// isEqual为true时表明中标的项目是这个人所中标的，不能自己投自己项目
			boolean isEqual = (userId == userIdInProject);
			System.out.println(isEqual);

			if (!user.equals("") && !project.equals("") && !isEqual) {
				System.out.println("更新中标表成功");
				return winDao.updateWinByProjectId(win);

			} else {
				System.out.println("插入中标表失败");
				return 0;
			}
		} else {
			System.out.println("插入中标表失败");
			return 0;
		}
	}

	@Override
	public int getWinCount() {
		return winDao.getWinCount();
	}

	/**
	 * @author 劳伟玲
	 * @version 创建时间：2017年8月1日14:45:07
	 * @introduction 查找指定用户的所有中标项目 与win表相关
	 */

	public List<Win> getAllProjectInWinByUser(User user) {
		// public List<Win> getAllProjectInWinByUser(){
		List<Project> projectListByUser = projectService.getTenderProjectByUserId(user.getUserId());
		// 然后再到win中查找指定用户项目的项目信息，根据projectId查找win
		List<Win> projectByUser = new ArrayList<Win>();
		for (int i = 0; i < projectListByUser.size(); i++) {
			Win win = winDao.getWinByWinByFKprojectId(projectListByUser.get(i).getProjectId());
			projectByUser.add(win);
		}

		return projectByUser;

	}

	/**
	 * @author 劳伟玲
	 * @version 创建时间：2017年8月1日14:45:07
	 * @introduction 查找指定用户的所有未中标项目 与win表相关
	 */

	public List<ShowRelationMap> getAllProjectNotInWinByUser(User user) {
		// public List<ShowRelationMap> getAllProjectNotInWinByUser(){
		// 先从所有项目中找到指定用户自己的所有招标项目的项目id集合(list)
		List<Project> projectListByUser = projectService.getNotTenderProjectByUserId(user.getUserId());
		List<ShowRelationMap> srmList = new ArrayList<ShowRelationMap>();
		List<Relation> reation = new ArrayList<Relation>();
		for (int i = 0; i < projectListByUser.size(); i++) {
			List<User> users = new ArrayList<User>();
			ShowRelationMap srm = new ShowRelationMap();
			srm.setProject(projectListByUser.get(i));
			/* srmList.get(i).setProject(projectListByUser.get(i)); */
			reation = relationDao.getRelationByFKPROJECTID(projectListByUser.get(i).getProjectId());
			for (int j = 0; j < reation.size(); j++) {
				users.add(reation.get(j).getUser());
			}
			srm.setListUser(users);

			srmList.add(srm);
		}

		System.out.println("srmList" + srmList);
		return srmList; // 里边装的是对象的对象

	}

}
