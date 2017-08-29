package com.lnsf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lnsf.service.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("getMaxEmpno")
	public void getMaxEmpno(){
		int max = employeeService.getMaxEmpno();
		System.out.println(max);
	}
}
