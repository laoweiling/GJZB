package com.lnsf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lnsf.entity.Project;
import com.lnsf.entity.Relation;

/**
 * @author 劳伟玲
 * @version 创建时间：2017年7月27日20:20:58
 * @introduction 创建招标接口RelationDao，注解实现招标的增删改查
 */
public interface RelationDao {
	// RelationDao需要增加的内容：
	// 删除项目类型需要
	// 根据projectId删除投标信息
	/**
	 * @author 黄浩贡
	 * @version 创建时间：2017年7月28日21:23:30
	 * @introduction 创建招标接口RelationDao，注解实现招标的增删改查
	 */
	// @Delete("delete from gjzb_relation where fkprojectId= #{fkprojectId}")
	// public int deleteRelationByProjectId(int fkprojectId);

	// 查询全部招标信息
	@Select("select * from gjzb_relation")
	@Results(value = { @Result(column = "RELATIONID", property = "relationId"),
			@Result(one = @One(select = "com.lnsf.dao.UserDao.getUserById"), column = "FKPUTID", property = "user"),
			@Result(one = @One(select = "com.lnsf.dao.ProjectDao.getProjectById"), column = "FKPROJECTID", property = "project") })
	List<Relation> getAllRelation();

	// 根据FKPROJECTID查找
	@Select("select * from gjzb_relation where FKPROJECTID=#{FKPROJECTID}")
	@Results(value = { @Result(column = "RELATIONID", property = "relationId"),
			@Result(one = @One(select = "com.lnsf.dao.UserDao.getUserById"), column = "FKPUTID", property = "user"),
			@Result(one = @One(select = "com.lnsf.dao.ProjectDao.getProjectById"), column = "FKPROJECTID", property = "project") })
	List<Relation> getRelationByFKPROJECTID(Integer FKPROJECTID);

	// 按招标id查询单个招标信息
	@Select("select * from gjzb_relation where relationId=#{relationId}")
	@Results(value = { @Result(column = "RELATIONID", property = "relationId"),
			@Result(one = @One(select = "com.lnsf.dao.UserDao.getUserById"), column = "FKPUTID", property = "user"),
			@Result(one = @One(select = "com.lnsf.dao.ProjectDao.getProjectById"), column = "FKPROJECTID", property = "project") })
	Relation getRelationById(Integer relationId);

	// 按招标id删除单个招标信息
	/**
	 * @author 黄卉
	 * @version 创建时间：2017年7月27日20:20:58
	 * @introduction 创建招标接口RelationDao，注解实现招标的增删改查
	 */
	@Delete("delete from gjzb_relation where relationId=#{relationId}")
	int deleteRelationById(Integer relationId);

	/**
	 * @author 黄卉
	 * @version 创建时间：2017年7月27日20:20:58
	 * @introduction 根据用户ID删除信息
	 */
	@Delete("delete from gjzb_relation where FKPUTID=#{FKPUTID}")
	int deleteRelationByUserId(Integer FKPUTID);

	/**
	 * @author 黄卉
	 * @version 创建时间：2017年7月27日20:20:58
	 * @introduction 根据项目ID删除信息
	 */
	@Delete("delete from gjzb_relation where FKPROJECTID=#{FKPROJECTID}")
	int deleteRelationByProjectId(Integer FKPROJECTID);

	// 按招标id更新单个招标信息
	@Update("update gjzb_relation set fkputId=#{user.userId},fkprojectId=#{project.projectId} where relationId=#{relationId}")
	int updateRelationById(Relation relation);

	// 插入单个招标信息
	@Insert("insert into gjzb_relation(relationId,fkputId,fkprojectId) values(relationseq.nextval,#{user.userId},#{project.projectId})")
	int insertRelation(Relation relation);

	// 查询relation表中是否有重复的数据
	@Select("select * from gjzb_relation where FKPUTID=#{FKPUTID} and FKPROJECTID=#{FKPROJECTID}")
	@Results(value = { @Result(column = "RELATIONID", property = "relationId"),
			@Result(one = @One(select = "com.lnsf.dao.UserDao.getUserById"), column = "FKPUTID", property = "user"),
			@Result(one = @One(select = "com.lnsf.dao.ProjectDao.getProjectById"), column = "FKPROJECTID", property = "project") })
	List<Relation> getRelationByuserIdAndProjectId(@Param("FKPUTID") Integer FKPUTID,
			@Param("FKPROJECTID") Integer FKPROJECTID);

	// 根据userId来查询该用户的所有已已投标信息
	@Select("select * from gjzb_relation where FKPUTID=#{userId}")
	@Results(value = { @Result(column = "RELATIONID", property = "relationId"),
			@Result(one = @One(select = "com.lnsf.dao.UserDao.getUserById"), column = "FKPUTID", property = "user") })
	List<Relation> getAllRelationsByUserId(Integer userId);

	// 根据userId查询所有对应的ProjectId
	@Select("select FKPROJECTID from gjzb_relation where FKPUTID=#{userId}")
	Integer getAllProjectsByUserIdFromRela(Integer userId);

	/**
	 * @author 黄卉
	 * @version 创建时间：2017年7月30日10:41:38
	 * @introduction 根据userid查找
	 */
	// 根据用户ID查找投标表信息
	@Select("select * from gjzb_relation where FKPUTID=#{FKPUTID}")
	@Results(value = { @Result(column = "RELATIONID", property = "relationId"),
			@Result(one = @One(select = "com.lnsf.dao.UserDao.getUserById"), column = "FKPUTID", property = "user"),
			@Result(one = @One(select = "com.lnsf.dao.ProjectDao.getProjectById"), column = "FKPROJECTID", property = "project") })
	List<Relation> getRelationByUserId(Integer FKPUTID);

	/**
	 * 根据用户id和项目id进行投标表的删除
	 * 
	 * @author 肖梦雅
	 */
	@Delete("delete from gjzb_relation where fkputId=#{user.userId} and fkprojectId = #{projectId}")
	public int delRelationByPidAndUserid(Project project);

	/**
	 * 分页查询，返回根据userId查找的项目信息
	 * 
	 * @author 肖梦雅
	 * @param
	 * @return
	 *//*
		 * @Select("select * from (select r.* rn from gjzb_relation r where rownum<=#{end} "
		 * + "and FKPUTID=#{FKPUTID}) e where rn>=#{start}")
		 * 
		 * @Results(value = { @Result(column = "RELATIONID", property =
		 * "relationId"),
		 * 
		 * @Result(one = @One(select = "com.lnsf.dao.UserDao.getUserById"),
		 * column = "FKPUTID", property = "user"),
		 * 
		 * @Result(one = @One(select =
		 * "com.lnsf.dao.ProjectDao.getProjectById"), column = "FKPROJECTID",
		 * property = "project") }) public List<Relation>
		 * getAllProjectsByUserIdPage(Page<Project> project_page,Integer
		 * FKPUTID);
		 */

	/**
	 * 分页查询的总数
	 * 
	 * @author 肖梦雅
	 * @param
	 * @return
	 */
	@Select("select count(fkprojectId) from gjzb_relation where FKPUTID=#{FKPUTID}")
	public int getTotalNum(Integer useId);

	/**
	 * 用于首页的报表，展示投标的数量
	 * 
	 * @author 梁肖萍
	 * @return
	 */
	@Select("select count(relationId) from gjzb_relation")
	public int getRelationCount();

}
