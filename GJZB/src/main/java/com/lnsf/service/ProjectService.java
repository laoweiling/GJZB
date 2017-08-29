package com.lnsf.service;

import java.util.List;

import com.lnsf.entity.BidCondition;
import com.lnsf.entity.Page;
import com.lnsf.entity.Project;

/**
 * @author 黄浩贡
 * @version 创建时间：2017年7月27日10:31:46
 * @introduction 增加几个关于项目修改的方法
 */
public interface ProjectService {

	// 查询所有信息
	public List<Project> getAllProject();

	// 根据projectID主键单条查找
	public Project getProjectById(Integer projectId);

	// 添加项目信息
	public Integer addProject(Project project);

	// 修改项目名字，截止时间，项目标价
	public int updateProjectName(Project project);

	public int updateProjectLastTime(Project project);

	public int updateProjectPrice(Project project);

	// 修改项目信息
	public int updateProject(Project project);

	// 根据用户ID删除本表信息
	public Integer deleteByProjectId(Integer PROJECTID);

	// 根据userId查找信息
	public List<Project> getProjectByUserId(Integer userId);

	// 分页查询（显示用户的已投标的项目信息）
	public List<Project> getAllProjectsByUserIdPage(Page<Project> project_page);

	// 模糊查询（根据用户输入的条件进行查询）
	public List<Project> getAllProjectsByConditionPage(BidCondition bidCondition);

	// 模糊查询分页查询的总数
	public int getTotalNum(BidCondition bidCondition);

	/**
	 * 伟玲 查找未中标的project(所有用户，主页涉及数据)
	 * 
	 * @return
	 */
	public List<Project> getProjectNotInWin();

	/**
	 * 页面修改项目的某些字段的需要 伟玲
	 * 
	 * @return
	 */
	public int updateProjectByID(Project project);

	/**
	 * 用于首页的报表，展示项目的数量
	 * 
	 * @author 梁肖萍
	 * @return
	 */
	public int getProjectCount();
	
	/**
	 * 添加项目时，获取项目的最大id数
	 * 
	 * @author 梁肖萍
	 * @return
	 */
	public int getMaxProjectId();

	/**
	 * 用于首页的展示5条项目信息
	 * 
	 * @author 梁肖萍
	 * @return
	 */
	public List<Project> getIndexProjects();

	/**
	 * 用于首页的热门项目信息
	 * 
	 * @author 梁肖萍
	 * @return
	 */
	public List<Project> getHotProjects();
}
