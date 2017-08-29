package com.lnsf.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lnsf.entity.BidCondition;
import com.lnsf.entity.Page;
import com.lnsf.entity.Project;
import com.lnsf.entity.User;
import com.lnsf.service.ProjectService;

/**
 * @author 黄卉
 * @version 创建时间：2017年7月29日 下午7:02:43
 * @introduction
 */

@Controller
@RequestMapping("/Project")
public class ProjectController {
	@Autowired
	ProjectService projectService;

	/**
	 * 根据userId,typeId，价格区间，项目名称进行模糊查询（sql拼接）
	 * 注：(若用户已登录，则投标人（用户）已投项目和已中标项目以及自己的项目是不能再进行投标)
	 * 
	 * @author 肖梦雅
	 * @return
	 */
	@RequestMapping("/getAllBidInfos")
	@ResponseBody
	public Page<Project> getAllBidInfos(BidCondition bidCondition, HttpServletRequest request, int page, int rows) {
		int userId = ((User) request.getSession().getAttribute("user")).getUserId();
		bidCondition.setUserId(userId);
		List<Project> projects = new ArrayList<Project>();
		Page<Project> project_page = new Page<>();
		bidCondition.setStart((page - 1) * rows + 1);
		bidCondition.setEnd(page * rows);
		String pName = "";
		try {// 解决页面传来的projectName乱码
			if (bidCondition.getProjectName() != null) {
				pName = new String((request.getParameter("projectName")).getBytes("iso-8859-1"), "utf-8");
				bidCondition.setProjectName(pName);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 输入的两个价格进行大小比较，使页面中的两个框可任意输入数值，不须考虑大小的顺序
		if (bidCondition.getMinPrice() != null && bidCondition.getMaxPrice() != null) {
			if (bidCondition.getMinPrice() > bidCondition.getMaxPrice()) {
				double tempPrice = bidCondition.getMaxPrice();
				bidCondition.setMaxPrice(bidCondition.getMinPrice());
				bidCondition.setMinPrice(tempPrice);
			}

		}
		// 获取符合条件的总条数
		int total = projectService.getTotalNum(bidCondition);
		projects = projectService.getAllProjectsByConditionPage(bidCondition);
		project_page.setTotal(total);
		project_page.setRows(projects);
		System.out.println("模糊查询 projects Conditon" + projects);
		return project_page;
	}

	/**
	 * 查找所有的项目信息
	 * 
	 * @author 劳伟玲
	 * @version 创建时间：2017年8月1日10:58:39
	 * @introduction 查找所有未中标的project信息 状态：通过测试
	 */
	@ResponseBody
	@RequestMapping("/getProjectNotInWin")
	public List<Project> getProjectNotInWin() {
		// 查询所有信息
		List<Project> projectList = projectService.getProjectNotInWin();
		return projectList;
	}

	/**
	 * 修改整个项目的某些字段
	 * 
	 * @author 劳伟玲
	 * @version 创建时间：2017年7月29日18:16:29
	 * @introduction 修改整个项目的某些字段
	 * 
	 */
	@ResponseBody
	@RequestMapping("/updateProjectBySome")
	public int updateProjectBySome(@ModelAttribute("project") Project project) {
		int projectnumber = projectService.updateProjectByID(project);
		System.out.println(projectnumber);
		return projectnumber;
	}

	/**
	 * @author 黄卉
	 * @version 创建时间：2017年7月29日 下午7:02:43
	 * @introduction 根据ID查找项目信息 状态：通过测试
	 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("/getProjectByUserId")
	 * //测试链接：http://localhost:8888/GJZB/Project/getProjectByUserId?userId=0
	 * public List<Project> getProjectByUserId(@RequestParam("userId")Integer
	 * userId){ List<Project>
	 * projectList=projectService.getProjectByUserId(userId); return
	 * projectList; } /**查找所有的项目信息
	 * 
	 * @author 黄卉
	 * 
	 * @version 创建时间：2017年7月29日19:12:43
	 * 
	 * @introduction 查找所有project信息 状态：通过测试
	 */
	/*
	 * //测试链接：http://localhost:8888/GJZB/Project/getAllProject
	 * 
	 * @ResponseBody
	 * 
	 * @RequestMapping("/getAllProject") public List<Project> getAllProject(){
	 * //查询所有信息 List<Project> projectList=projectService.getAllProject(); return
	 * projectList; } /**根据项目ID查找项目信息
	 * 
	 * @author 黄卉
	 * 
	 * @version 创建时间：2017年7月29日19:14:48
	 * 
	 * @introduction 状态：通过测试
	 *//*
		 * @ResponseBody
		 * 
		 * @RequestMapping("/getProjectById")
		 * //测试链接：http://localhost:8888/GJZB/Project/getProjectById?projectId=40
		 * public Project getProjectById(@RequestParam("projectId")Integer
		 * projectId){ //根据projectID主键单条查找 Project project =
		 * projectService.getProjectById(projectId); return project; }
		 *//**
		 * 添加项目信息
		 * 
		 * @author 黄卉
		 * @version 创建时间：2017年7月29日19:14:48
		 * @introduction 状态：未测试
		 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("/addProject") public Integer
	 * addProject(@ModelAttribute("project")Project project){ int projectnumber
	 * = projectService.addProject(project); return projectnumber; }
	 * 
	 *//**
		 * 修改项目信息，所有信息一同修改
		 * 
		 * @author 黄卉
		 * @version 创建时间：2017年7月29日18:16:29
		 * @introduction 修改整个项目 状态：没测试
		 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("/updateProject") public int
	 * updateProject(@ModelAttribute("project")Project project){ int
	 * projectnumber = projectService.updateProject(project); return
	 * projectnumber; }
	 *//**
		 * @author 黄卉
		 * @version 创建时间：2017年7月29日16:51:00
		 * @introduction 查找所有project信息 状态：通过测试
		 *//*
		 * //post提交
		 * 
		 * @ResponseBody
		 * 
		 * @RequestMapping("/deleteByProjectId") public Integer
		 * deleteByProjectId(@RequestParam("PROJECTID")Integer PROJECTID){
		 * //模拟测试链接：http://localhost:8888/GJZB/Project/deleteByProjectId?
		 * PROJECTID=40 int projectnumber =
		 * projectService.deleteByProjectId(PROJECTID); return projectnumber; }
		 */

	/**
	 * 查看当个项目的详细信息，根据projectId来查询项目
	 * 
	 * @author 梁肖萍
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value = "getProjectByProjectId", produces = "application/json;chartset=utf-8")
	public String getProjectByProjectId(Integer projectId, Model model) {
		Project project = projectService.getProjectById(projectId);
		model.addAttribute("project", project);
		return "jsp/project/projectInfo";
	}
}
