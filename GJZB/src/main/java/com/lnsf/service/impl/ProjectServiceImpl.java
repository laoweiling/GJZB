package com.lnsf.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lnsf.dao.ProjectDao;
import com.lnsf.dao.RelationDao;
import com.lnsf.dao.UserDao;
import com.lnsf.dao.WinDao;
import com.lnsf.entity.BidCondition;
import com.lnsf.entity.Page;
import com.lnsf.entity.Project;
import com.lnsf.entity.Relation;
import com.lnsf.entity.User;
import com.lnsf.entity.Win;
import com.lnsf.service.ProjectService;

/**
 * @author 黄浩贡
 * @version 创建时间：2017年7月27日10:46:31
 * @introduction 增加projectservice实现类
 */
@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private WinDao winDao;
	@Autowired
	private RelationDao relationDao;

	/**
	 * 个人用户ID查找项目信息
	 * 
	 * @author 黄卉
	 * @version 创建时间：2017年7月29日16:51:00
	 * @introduction 查找所有project信息 通过测试
	 */
	@Override
	public List<Project> getProjectByUserId(Integer userId) {
		return projectDao.getProjectByUserId(userId);
	}

	/**
	 * 查找所有的项目信息
	 * 
	 * @author 黄卉
	 * @version 创建时间：2017年7月29日16:51:00
	 * @introduction 查找所有project信息 通过测试
	 */
	@Override
	public List<Project> getAllProject() {
		// 查询所有信息
		return projectDao.getAllProject();
	}

	/**
	 * 根据项目ID查找项目信息
	 * 
	 * @author 黄卉
	 * @version 创建时间：2017年7月29日16:51:00
	 * @introduction 查找所有project信息 通过测试
	 */
	@Override
	public Project getProjectById(Integer projectId) {
		// 根据projectID主键单条查找
		return projectDao.getProjectById(projectId);
	}

	/**
	 * 根据项目ID查找项目信息
	 * 
	 * @author 黄浩贡
	 * @version 创建时间：2017年7月27日10:46:31
	 * @introduction 修改项目名称
	 * 
	 */
	@Override
	public int updateProjectName(Project project) {
		// TODO Auto-generated method stub
		Project getProject = projectDao.getProjectById(project.getProjectId());
		User getUser = userDao.getUserById(project.getUser().getUserId());
		if ((getProject != null && !("").equals(getProject)) && (getUser != null && !("").equals(getUser))) {

			return projectDao.updateProjectNameById(project);
		} else {
			return 0;
		}
	}

	/**
	 * 根据项目ID查找项目信息
	 * 
	 * @author 黄浩贡
	 * @version 创建时间：2017年7月27日10:46:31
	 * @introduction 修改最后一次时间
	 * 
	 */
	@Override
	public int updateProjectLastTime(Project project) {
		// TODO Auto-generated method stub
		Project getProject = projectDao.getProjectById(project.getProjectId());
		User getUser = userDao.getUserById(project.getUser().getUserId());
		if ((getProject != null && !("").equals(getProject)) && (getUser != null && !("").equals(getUser))) {

			return projectDao.updateProjectLastTimeById(project);
		} else {
			return 0;
		}
	}

	/**
	 * 根据项目ID查找项目信息
	 * 
	 * @author 黄浩贡
	 * @version 创建时间：2017年7月27日10:46:31
	 * @introduction 修改价格
	 * 
	 */
	@Override
	public int updateProjectPrice(Project project) {
		// TODO Auto-generated method stub
		Project getProject = projectDao.getProjectById(project.getProjectId());
		User getUser = userDao.getUserById(project.getUser().getUserId());
		if ((getProject != null && !("").equals(getProject)) && (getUser != null && !("").equals(getUser))) {

			return projectDao.updateProjectPriceById(project);
		} else {
			return 0;
		}
	}

	/**
	 * 测试：添加项目代码
	 * 
	 * @author 黄卉
	 * @version 创建时间：2017年7月29日18:14:43
	 * @introduction 添加方法，添加项目 删除项目，通过测试
	 */
	@Override
	public Integer addProject(Project project) {
		User getUser = userDao.getUserById(project.getUser().getUserId());
		if ((getUser != null && !("").equals(getUser))) {
			return projectDao.addProject(project);
		} else {
			return 0;
		}
	}

	/**
	 * 修改项目信息，所有信息一同修改
	 * 
	 * @author 黄卉
	 * @version 创建时间：2017年7月29日18:16:29
	 * @introduction 修改整个项目 删除项目，通过测试
	 */
	public int updateProject(Project project) {
		Project getProject = projectDao.getProjectById(project.getProjectId());
		User getUser = userDao.getUserById(project.getUser().getUserId());

		if ((getProject != null && !("").equals(getProject)) && (getUser != null && !("").equals(getUser))) {
			System.out.println("修改：" + getProject);
			return projectDao.updateProject(project);
		} else {
			return 0;
		}
	}

	/**
	 * @author 黄卉
	 * @version 创建时间：2017年7月29日16:51:00
	 * @introduction 查找所有project信息 删除项目，通过测试
	 */
	// 根据用户ID删除本表信息
	@Override
	public Integer deleteByProjectId(Integer PROJECTID) {
		// 首先删除中标表和投标表的信息
		// 根据项目ID查找中标表项目信息
		Win numwin = winDao.getWinByWinByFKprojectId(PROJECTID);
		// 根据项目ID查找投标表信息
		List<Relation> numRelation = relationDao.getRelationByFKPROJECTID(PROJECTID);
		Project getProject = projectDao.getProjectById(PROJECTID);
		if (getProject != null && (!("").equals(getProject))) {
			if (numwin != null && (!("").equals(numwin)) && numRelation.size() > 0) {
				System.out.println("删除中标表信息winDao:" + winDao.deleteWinByProjectId(PROJECTID));
				for (int i = 0; i < numRelation.size(); i++) {
					System.out.println("删除投标表信息relationDao_" + i + "_:"
							+ relationDao.deleteRelationByProjectId(numRelation.get(i).getProject().getProjectId()));
				}

			}
			// 最后项目表
			return projectDao.deleteByProjectId(PROJECTID);
		} else {
			return 0;
		}
	}

	@Override
	public int getProjectCount() {
		return projectDao.getProjectCount();
	}

	@Override
	public List<Project> getIndexProjects() {
		return projectDao.getIndexProjects();
	}

	@Override
	public List<Project> getAllProjectsByUserIdPage(Page<Project> project_page) {
		return projectDao.getAllProjectsByUserIdPage(project_page);
	}

	@Override
	public List<Project> getAllProjectsByConditionPage(BidCondition bidCondition) {
		return projectDao.getAllProjectsByConditionPage(bidCondition);
	}

	@Override
	public int getTotalNum(BidCondition bidCondition) {
		return projectDao.getTotalNum(bidCondition);
	}

	@Override
	public List<Project> getHotProjects() {
		return projectDao.getHotProjects();
	}

	/**
	 * @author 劳伟玲
	 * @version 创建时间：2017年8月1日10:37:28
	 * @introduction 查找未中标的project （与win表相关）
	 */
	@Override
	public List<Project> getProjectNotInWin() {
		List<Project> ProjectList = projectDao.getAllProject();
		List<Win> winsList = winDao.getAllWin();
		List<Project> ProjectnotInWinList = new ArrayList<Project>();
		List<Integer> ProjectIdNotInWinList = new ArrayList<Integer>();
		List<Integer> projectIdInWinlList = new ArrayList<Integer>();
		List<Integer> projectIdInProjectList = new ArrayList<Integer>();

		for (int i = 0; i < ProjectList.size(); i++) {
			projectIdInProjectList.add(ProjectList.get(i).getProjectId());
		}
		// 存储在win中的projectID
		for (int i = 0; i < winsList.size(); i++) {
			projectIdInWinlList.add(winsList.get(i).getProject().getProjectId());
		}
		// 查找出不同的
		System.out.println(projectIdInProjectList);
		for (int pl : projectIdInProjectList) {
			if (!projectIdInWinlList.contains(pl)) {
				ProjectIdNotInWinList.add(pl);
			}

		}
		System.out.println(ProjectIdNotInWinList);
		// 然后再到project中查找未中标的项目
		for (int i = 0; i < ProjectIdNotInWinList.size(); i++) {
			Project project = projectDao.getProjectById(ProjectIdNotInWinList.get(i));
			ProjectnotInWinList.add(project);
		}
		System.out.println(ProjectnotInWinList);

		return ProjectnotInWinList;
	}

	/**
	 * 修改页面项目信息的某些字段
	 * 
	 * @author 劳伟玲
	 * @version 创建时间：2017年7月29日18:16:29
	 * @introduction 修改项目的某些字段
	 */
	public int updateProjectByID(Project project) {
		String projectName = project.getProjectName();
		Double projectprice = project.getPrice();

		Project getProject = projectDao.getProjectById(project.getProjectId());
		getProject.setProjectName(projectName);
		getProject.setPrice(projectprice);
		return projectDao.updateProjectByProjectID(getProject);
	}

	@Override
	public int getMaxProjectId() {
		return projectDao.getMaxProjectId();
	}
}
