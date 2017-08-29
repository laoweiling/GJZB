package com.lnsf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lnsf.dao.EmployeeDao;
import com.lnsf.service.EmployeeService;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public int getMaxEmpno() {
		return employeeDao.getMaxEmpno();
	}

}
