package com.lnsf.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.lnsf.entity.Nav;
import com.lnsf.service.NavService;

@Controller
public class NavController {
	@Autowired
	private NavService navService;

	@RequestMapping(value = "nav", produces = "application/json;charset=utf-8")
	@ResponseBody()
	public String getNav(String userlevel) {
		List<Nav> navList = new ArrayList<>();
		int level = Integer.parseInt(userlevel);
		if (level == 1) {
			navList = navService.getAllList();
		} else {
			navList = navService.getListByCommon();
		}
		Gson gson = new Gson();

		return gson.toJson(navList);
	}
}
