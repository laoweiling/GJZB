package com.lnsf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.lnsf.entity.BidCondition;
import com.lnsf.entity.Page;
import com.lnsf.entity.Project;

/**
* @author 黄浩贡  黄卉
* @version 创建时间：2017年7月27日10:29:21
* @introduction  实现project的修改操作
*/
public interface ProjectDao {
	/**
	 * @author黄浩贡
	 * @version 创建时间：22017年7月28日21:23:30
	 * @introduction 创建type的增删改查操作
	 *
	 */
	// 用于删除类型信息
	// 根据TypeID查找到所有该类型的项目
	@Select("select * from gjzb_project where fktypeId = #{fktypeId}")
	public List<Project> getProjectByTypeId(int fktypeId);

	/**
	 * @author黄浩贡
	 * @version 创建时间：22017年7月28日21:23:30
	 * @introduction 创建type的增删改查操作
	 *
	 */
	// 根据typeID删除该类型的项目
	@Delete("delete from gjzb_project where fktypeId = #{fktypeId}")
	public int deleteProjectByTypeId(int fktypeId);

	// 查找所有project
	@Select("select * from gjzb_project")
	@Results(value = { @Result(column = "projectId", property = "projectId"),
			@Result(one = @One(select = "com.lnsf.dao.UserDao.getUserById"), column = "FKUSERID", property = "user"),
			@Result(column = "projectName", property = "projectName"),
			@Result(column = "releaseTime", property = "releaseTime"),
			@Result(column = "lastTime", property = "lastTime"),
			@Result(column = "projectcontent", property = "projectcontent"),
			@Result(column = "price", property = "price"),
			@Result(one = @One(select = "com.lnsf.dao.TypeDao.getTypeById"), column = "FKTYPEID", property = "type"), })
	public List<Project> getAllProject();

	/**
	 * @author 黄卉
	 * @version 创建时间：2017年7月29日10:33:49
	 * @introduction 根据userID查询信息，查找projectID信息
	 */
	@Select("select * from gjzb_project where FKUSERID=#{userId}")
	@Results(value = { @Result(column = "projectId", property = "projectId"),
			@Result(one = @One(select = "com.lnsf.dao.UserDao.getUserById"), column = "FKUSERID", property = "user"),
			@Result(column = "projectName", property = "projectName"),
			@Result(column = "releaseTime", property = "releaseTime"),
			@Result(column = "lastTime", property = "lastTime"),
			@Result(column = "projectcontent", property = "projectcontent"),
			@Result(column = "price", property = "price"),
			@Result(one = @One(select = "com.lnsf.dao.TypeDao.getTypeById"), column = "FKTYPEID", property = "type"), })
	public List<Project> getProjectByUserId(Integer userId);

	// 根据project的查找ID
	@Select("select * from gjzb_project where projectId=#{projectId}")
	@Results(value = { @Result(column = "projectId", property = "projectId"),
			@Result(one = @One(select = "com.lnsf.dao.UserDao.getUserById"), column = "FKUSERID", property = "user"),
			@Result(column = "projectName", property = "projectName"),
			@Result(column = "releaseTime", property = "releaseTime"),
			@Result(column = "lastTime", property = "lastTime"),
			@Result(column = "projectcontent", property = "projectcontent"),
			@Result(column = "price", property = "price"),
			@Result(one = @One(select = "com.lnsf.dao.TypeDao.getTypeById"), column = "FKTYPEID", property = "type"), })
	public Project getProjectById(Integer projectId);

	/**
	 * 添加方法，添加项目
	 * 
	 * @author 黄卉
	 * @version 创建时间：2017年7月29日10:33:49
	 * @introduction 添加方法，添加项目
	 */
	@Insert("insert into gjzb_project(projectId,fktypeId,fkuserId,projectName,releaseTime,lastTime,projectcontent,price) values(projectseq.nextval,"
			+ "#{type.typeId},#{user.userId},#{projectName},#{releaseTime},#{lastTime}"
			+ ",#{projectcontent},#{price})")
	public Integer addProject(Project project);

	/**
	 * 删除方法,根据项目ID删除
	 * 
	 * @author 黄卉
	 * @version 创建时间：2017年7月29日10:33:49
	 * @introduction 根据projectID删除信息。单表，删除，需要现在业务逻辑处理好删除的关联表
	 */
	@Delete("delete from gjzb_project where PROJECTID=#{PROJECTID}")
	public Integer deleteByProjectId(Integer PROJECTID);

	/**
	 * 删除方法,根据用户ID删除
	 * 
	 * @author 黄卉
	 * @version 创建时间：2017年7月29日09:45:26
	 * @introduction 实现project的修改操作
	 */
	@Delete("delete from gjzb_project where FKUSERID=#{FKUSERID}")
	public Integer deleteWinByUserId(Integer FKUSERID);

	// 根据Id修改项目名字
	@Update("update gjzb_project set projectName =#{projectName} where projectId =#{projectId}")
	public int updateProjectNameById(Project project);

	// 根据Id修改截止时间
	@Update("update gjzb_project set lastTime =#{lastTime} where projectId =#{projectId}")
	public int updateProjectLastTimeById(Project project);

	// 根据Id修改项目标价
	@Update("update gjzb_project set price =#{price} where projectId =#{projectId}")
	public int updateProjectPriceById(Project project);

	// 根据Id修改所有项目信息
	@Update("update gjzb_project " + "set fkuserId  =#{user.userId}" + ", fktypeId =#{type.typeId}"
			+ ", projectName  =#{projectName}" + ", ReleaseTime =#{releaseTime}" + ", lastTime  =#{lastTime}"
			+ ", projectcontent  =#{projectcontent}" + ", price =#{price} " + " where projectId  =#{projectId}")
	public int updateProject(Project project);

	/**
	 * 分页查询Project，获取用户的id
	 * 
	 * @author 肖梦雅
	 * @param
	 * @return
	 */
	@Select("select * from (select p.*,rownum rn from gjzb_project p where rownum<=#{end} and p.projectId in( "
			+ "select fkprojectId from gjzb_relation where FKPUTID=#{total})) e  where rn>=#{start}")
	public List<Project> getAllProjectsByUserIdPage(Page<Project> page);

	/**
	 * 根据条件分页模糊查询
	 * 
	 * @author 肖梦雅
	 * @param
	 * @return
	 */
	@SelectProvider(type = com.lnsf.dao.BidSqlProvider.class, method = "getProjectsByCondition")
	public List<Project> getAllProjectsByConditionPage(BidCondition bidCondition);

	/**
	 * 模糊查询分页的总条数
	 * 
	 * @author 肖梦雅
	 * @param
	 * @return
	 */
	@SelectProvider(type = com.lnsf.dao.BidSqlProvider.class, method = "getTotalNum")
	public int getTotalNum(BidCondition bidCondition);

	/**
	 * 根据Id修改项目的名称和价钱
	 * @author 劳伟玲
	 * @param project
	 * @return
	 */
	@Update("update gjzb_project " + "set "
			+ "projectName  =#{projectName}"
			+ ", price =#{price} " + " where projectId  =#{projectId}")
	public int updateProjectByProjectID(Project project);

	/**
	 * 查找project，查找未中标的
	 * @param userId
	 * @author 劳伟玲
	 * @return
	 */
	@Select("select * from gjzb_project where FKUSERID=#{userId} and projectId not in(select fkprojectId from gjzb_win)")
	@Results(value={
			@Result(column="projectId",property="projectId"),
			@Result(one=@One(select="com.lnsf.dao.UserDao.getUserById"),column="FKUSERID",property="user"),
			@Result(column="projectName",property="projectName"),
			@Result(column="releaseTime",property="releaseTime"),
			@Result(column="lastTime",property="lastTime"),
			@Result(column="projectcontent",property="projectcontent"),
			@Result(column="price",property="price"),
			@Result(one=@One(select="com.lnsf.dao.TypeDao.getTypeById"),column="FKTYPEID",property="type"),
		})
	public List<Project> getNotTenderProjectByUserId(Integer userId);
	
	/**
	 * 查找project，查找已中标的
	 * @param userId
	 * @author 劳伟玲
	 * @return
	 */
	@Select("select * from gjzb_project where FKUSERID=#{userId} and projectId  in(select fkprojectId from gjzb_win)")
	@Results(value={
			@Result(column="projectId",property="projectId"),
			@Result(one=@One(select="com.lnsf.dao.UserDao.getUserById"),column="FKUSERID",property="user"),
			@Result(column="projectName",property="projectName"),
			@Result(column="releaseTime",property="releaseTime"),
			@Result(column="lastTime",property="lastTime"),
			@Result(column="projectcontent",property="projectcontent"),
			@Result(column="price",property="price"),
			@Result(one=@One(select="com.lnsf.dao.TypeDao.getTypeById"),column="FKTYPEID",property="type"),
		})
	public List<Project> getTenderProjectByUserId(Integer userId);
	
	/**
	 * 添加项目时，获取项目的最大id数
	 * 
	 * @author 梁肖萍
	 * @return
	 */
	@Select("select max(projectId) from gjzb_project")
	public int getMaxProjectId();
	
	/**
	 * 用于首页的报表，展示项目的数量
	 * 
	 * @author 梁肖萍
	 * @return
	 */
	@Select("select count(projectId) from gjzb_project")
	public int getProjectCount();
	
	/**
	 * 用于首页的展示5条项目信息
	 * @author 梁肖萍
	 * @return
	 */
	@Select("select p.*,rownum from  (select * from GJZB_PROJECT where PROJECTID in ("
			+"select PROJECTID from GJZB_PROJECT  minus select FKPROJECTID from gjzb_win)"
			+" order by releaseTime desc )p where rownum<=5")
	public List<Project> getIndexProjects();
	
	/**
	 * 用于首页热门项目信息
	 * @author 梁肖萍
	 * @return
	 */
	@Select("select p.* ,rownum from gjzb_project p where projectId in("+
       "select fkprojectId from ("
			+"select r.fkprojectId from gjzb_relation r group by r.fkprojectId order by r.fkprojectId )where rownum<=5)")
	public List<Project> getHotProjects();
	
	
	
}
