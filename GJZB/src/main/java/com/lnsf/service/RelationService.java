package com.lnsf.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lnsf.entity.Page;
import com.lnsf.entity.Project;
import com.lnsf.entity.Relation;

/**
 * @author 劳伟玲
 * @version 创建时间：2017年7月27日22:44:52
 * @introduction 创建招标业务层接口RelationService
 */
public interface RelationService {
	public int delRelationByPidAndUseridService(Project project);
	/**
	* @author 劳伟玲 
	* @version 创建时间：2017年7月27日22:44:52
	* @introduction  创建招标业务层接口RelationService
	*/
		//查询全部招标信息
		List<Relation> getAllRelation();
				
		//按招标id查询单个招标信息
		Relation getRelationById(Integer relationId);
				
				
		//按招标id删除单个招标信息
		int deleteRelationById(Integer relationId);
		
		//根据用户ID去删除信息	
		int deleteRelationByUserId(Integer FKPUTID);
		//根据项目ID删除
		int deleteRelationByProjectId(Integer FKPROJECTID);
		
		//按招标id更新单个招标信息
		int updateRelationById(Relation relation);
				
		//插入单个招标信息
		int insertRelation(Relation relation);
		
		List<Relation> getRelationByuserIdAndProjectId(@Param("FKPUTID") Integer FKPUTID, @Param("FKPROJECTID") Integer FKPROJECTID);
		
		//根据用户id查找投标信息
		List<Relation> getAllRelationsByUserId(Integer userId);
		
		//根据用户id查找投标信息（分页）
		List<Relation> getAllProjectsByUserIdPage(Page<Project> project_page,Integer FKPUTID);

		//根据userId获取用户的投标的项目的总数
		int getCountTotalNum(Integer userId);

	/**
	 * 用于首页的报表，展示投标的数量
	 * 
	 * @author 梁肖萍
	 * @return
	 */
	public int getRelationCount();

}
