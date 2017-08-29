package com.lnsf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lnsf.entity.Win;
import org.apache.ibatis.annotations.Param;

/**
 * @author 黄卉
 * @version 创建时间：2017年7月28日 上午11:53:55
 * @introduction 中标持久层
 */
public interface WinDao {
	// 查询全部用户信息
	@Select("select * from gjzb_win")
	@Results(value = { @Result(column = "winId", property = "winId"),
			// @Result(column="projectId",property="projectId"),
			@Result(one = @One(select = "com.lnsf.dao.UserDao.getUserById"), column = "FKGETID", property = "user"),
			@Result(one = @One(select = "com.lnsf.dao.ProjectDao.getProjectById"), column = "FKPROJECTID", property = "project") })
	List<Win> getAllWin();

	// 根据主键ID查找信息
	@Select("select * from gjzb_win where winId=#{winId}")
	@Results(value = { @Result(column = "winId", property = "winId"),
			// @Result(column="projectId",property="projectId"),
			@Result(one = @One(select = "com.lnsf.dao.UserDao.getUserById"), column = "FKGETID", property = "user"),
			@Result(one = @One(select = "com.lnsf.dao.ProjectDao.getProjectById"), column = "FKPROJECTID", property = "project") })
	Win getWinByWinId(Integer winId);

	// 根据项目ID查找中标信息
	@Select("select * from gjzb_win where FKPROJECTID=#{fkprojectId}")
	@Results(value = { @Result(column = "winId", property = "winId"),
			// @Result(column="projectId",property="projectId"),
			@Result(one = @One(select = "com.lnsf.dao.UserDao.getUserById"), column = "FKGETID", property = "user"),
			@Result(one = @One(select = "com.lnsf.dao.ProjectDao.getProjectById"), column = "FKPROJECTID", property = "project") })
	Win getWinByWinByFKprojectId(Integer fkprojectId);

	// 根据用户ID查找中标信息
	@Select("select * from gjzb_win where FKGETID=#{userId}")
	@Results(value = { @Result(column = "winId", property = "winId"),
			// @Result(column="projectId",property="projectId"),
			@Result(one = @One(select = "com.lnsf.dao.UserDao.getUserById"), column = "FKGETID", property = "user"),
			@Result(one = @One(select = "com.lnsf.dao.ProjectDao.getProjectById"), column = "FKPROJECTID", property = "project") })
	Win getWinByWinByFKUserId(Integer userId);

	// 插入信息
	@Insert("insert into gjzb_win (WINID,FKGETID,FKPROJECTID)values(winseq.nextval,#{user.userId},#{project.projectId})")
	Integer addWin(Win win);

	// 根据主键ID删除信息
	@Delete("delete from gjzb_win where WINID=#{WINID}")
	Integer deleteWinById(Integer WINID);

	// 根据用户ID删除信息
	@Delete("delete from gjzb_win where FKGETID=#{FKGETID}")
	Integer deleteWinByUserId(Integer FKGETID);

	// 根据项目ID删除信息
	@Delete("delete from gjzb_win where FKPROJECTID=#{FKPROJECTID}")
	Integer deleteWinByProjectId(Integer FKPROJECTID);

	// 修改信息,根据win主键ID修改信息
	@Update("update gjzb_win set FKGETID=#{user.userId},FKPROJECTID=#{project.projectId} where  WINID=#{winId}")
	Integer updateWinByProjectId(Win win);

	// 查询win表中是否有重复数据
	@Select("select * from gjzb_win where FKGETID=#{FKGETID} and FKPROJECTID=#{FKPROJECTID}")
	@Results(value = { @Result(column = "winId", property = "winId"),
			@Result(one = @One(select = "com.lnsf.dao.UserDao.getUserById"), column = "FKGETID", property = "user"),
			@Result(one = @One(select = "com.lnsf.dao.ProjectDao.getProjectById"), column = "FKPROJECTID", property = "project") })
	List<Win> getWinByByUserIdAndProjectId(@Param("FKGETID") Integer FKGETID,
			@Param("FKPROJECTID") Integer FKPROJECTID);

	/**
	 * 用于首页的报表，展示中标的数量
	 * 
	 * @author 梁肖萍
	 * @return
	 */
	@Select("select count(winId) from gjzb_win")
	public int getWinCount();

}
