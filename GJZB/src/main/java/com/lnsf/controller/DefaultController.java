package com.lnsf.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lnsf.entity.Default;
import com.lnsf.entity.Project;
import com.lnsf.entity.User;
import com.lnsf.service.ProjectService;
import com.lnsf.service.RelationService;
import com.lnsf.service.UserService;
import com.lnsf.service.WinService;
/**
 * 用于首页的信息展示
 * @author 梁肖萍
 *
 */
@Controller
public class DefaultController {
	@Autowired
	private WinService winService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private UserService userService;
	@Autowired
	private RelationService relationService;
	
	/**
	 * 报表
	 * @return
	 */
	public Map<String, Integer> gvChart(){
		int winCount = winService.getWinCount();
		int projectCount = projectService.getProjectCount();
		int userCount = userService.getCount();
		int relationCount = relationService.getRelationCount();
		
		Map<String, Integer> map = new HashMap<>();
		map.put("中标人数", winCount);
		map.put("招标数", projectCount);
		map.put("投标数", relationCount);
		map.put("用户", userCount);
		return map;
	}
	
	/**
	 * 用于首页的展示5条项目信息，要排除中标的项目
	 * @author 梁肖萍
	 * @return
	 */
	public List<Project> getIndexProjects(){
		List<Project> projects = projectService.getIndexProjects();
		return projects;
	}
	
	/**
	 * 用于首页的热门项目
	 * @author 梁肖萍
	 * @return
	 */
	public List<Project> getHotProjects(){
		List<Project> projects = projectService.getHotProjects();
		return projects;
	}
	
	/**
	 * 大神级别的用户
	 * @author 梁肖萍
	 * @return
	 */
	public List<User> getHotUsers(){
		List<User> list = userService.getHotUser();
		return list;
	}
	
	/**
	 * 用于返回首页default.jsp
	 * @return
	 */
	@RequestMapping(value="getDefault",produces="application/json;chartset=utf-8")
	@ResponseBody
	public Default getDefault(){
		Default default1 = new Default();
		default1.setMap(gvChart());
		default1.setProject(getIndexProjects());
		default1.setHot_pro(getHotProjects());
		default1.setHot_user(getHotUsers());
		return default1;
		
	}
}
