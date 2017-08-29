package com.lnsf.dao;

import org.apache.ibatis.annotations.Select;

public interface EmployeeDao {
	// 获取Emp表中empno的最大值
	@Select("select max(empno) from emp")
	public int getMaxEmpno();
}
