package com.lnsf.dao;

import com.lnsf.entity.BidCondition;
import com.lnsf.entity.Page;
import com.lnsf.entity.Project;

public class BidSqlProvider {
	/**
	 * 根据BidCondition类中的字段来拼接sql语句 注：(若用户已登录，则投标人（用户）已投项目和已中标项目以及自己的项目是不能再进行投标)
	 * 
	 * @author 肖梦雅
	 * @param
	 */
	public String getProjectsByCondition(BidCondition bidCondition) {
		String sql = "select * from (select p.*,rownum rn from gjzb_project p where rownum<=#{end} and "
				     + "p.projectId in  (select PROJECTID from GJZB_PROJECT"
				     + " MINUS select FKPROJECTID from  gjzb_win minus select FKPROJECTID from GJZB_relation ";
		String sqlWhere = "";
		// 有用户登录，userId不为空，需要
		if (bidCondition.getUserId() != null) {
			sqlWhere += "where FKPUTID= " + bidCondition.getUserId() + ")";
		}
		// 无用户登录,把已中标的标除去
		else if (bidCondition.getUserId() == null) {
			sql = "select * from (select p.*,rownum rn from gjzb_project p where rownum<=4 and "
				     + "p.projectId in (select PROJECTID from GJZB_PROJECT"
					+ " MINUS select FKPROJECTID from  gjzb_win)";
		}
		if (bidCondition.getMinPrice() != null) {
			sqlWhere += " and price >=" + bidCondition.getMinPrice() + " ";
		}
		if (bidCondition.getMaxPrice() != null) {
			sqlWhere += " and price <=" + bidCondition.getMaxPrice() + " ";
		}
		if (bidCondition.getTypeId() != null) {
			sqlWhere += " and fktypeId=" + bidCondition.getTypeId() + " ";
		}
		if (bidCondition.getProjectName() != null && !bidCondition.getProjectName().equals("")) {
			sqlWhere += "or  projectName like '%" + bidCondition.getProjectName() + "%'";
		}

		sql = sql + sqlWhere +") e  where rn>=#{start}";
		System.out.println("sql: " + sql);
		return sql;
	}



	/**
	 * 分页获取符合条件的总条数
	 * 注：(若用户已登录，则投标人（用户）已投项目和已中标项目以及自己的项目是不能再进行投标)
	 * @author 肖梦雅
	 * @param 
	 * @return
	 */
	public String  getTotalNum(BidCondition bidCondition) {
		String sql = "select count(PROJECTID) from GJZB_PROJECT where PROJECTID in " + "(select PROJECTID from GJZB_PROJECT"
				+ " MINUS select FKPROJECTID from  gjzb_win " + "minus select FKPROJECTID from GJZB_relation ";
		String sqlWhere = "";
		// 有用户登录，userId不为空，需要
		if (bidCondition.getUserId() != null) {
			sqlWhere += "where FKPUTID= " + bidCondition.getUserId() + ")";
		}
		// 无用户登录,把已中标的标除去
		else if (bidCondition.getUserId() == null) {
			sql = "select * from GJZB_PROJECT where PROJECTID in " + "(select PROJECTID from GJZB_PROJECT"
					+ " MINUS select FKPROJECTID from  gjzb_win)";
		}
		if (bidCondition.getMinPrice() != null) {
			sqlWhere += " and price >=" + bidCondition.getMinPrice() + " ";
		}
		if (bidCondition.getMaxPrice() != null) {
			sqlWhere += " and price <=" + bidCondition.getMaxPrice() + " ";
		}
		if (bidCondition.getTypeId() != null) {
			sqlWhere += " and fktypeId=" + bidCondition.getTypeId() + " ";
		}
		if (bidCondition.getProjectName() != null && !bidCondition.getProjectName().equals("")) {
			sqlWhere += "or  projectName like '%" + bidCondition.getProjectName() + "%'";
		}

		sql = sql + sqlWhere;
		System.out.println("sqlNUm: " + sql);
		return sql;
	}
	
	
	
}
