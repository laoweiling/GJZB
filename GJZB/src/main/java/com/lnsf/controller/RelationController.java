package com.lnsf.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lnsf.entity.Judge;
import com.lnsf.entity.Page;
import com.lnsf.entity.Project;
import com.lnsf.entity.Relation;
import com.lnsf.entity.User;
import com.lnsf.entity.Win;
import com.lnsf.service.ProjectService;
import com.lnsf.service.RelationService;
import com.lnsf.service.UserService;
import com.lnsf.service.WinService;
import com.lnsf.service.impl.RelationServiceImpl;
import com.lnsf.util.RC4Encrypt;

@Controller
@RequestMapping("/relation")
public class RelationController {
	
		 
	      @Autowired
		  private RelationService relationService; 
	      
	      @Autowired
	      ProjectService projectService;
	      
	      @Autowired
	      WinService winService;
		  
	      @ResponseBody
		  @RequestMapping("/showRelationById")  
		  public Relation showRelationById(HttpServletRequest request,Model model){ 	       
		        Relation relation = this.relationService.getRelationById(2); 
		     //   model.addAttribute("relation", relation);  
		    //    return "showRelationById";  
		        return relation;
		  } 
	    
	      @ResponseBody
		  @RequestMapping("/showAllRelations")  
		  public List<Relation> showAllRelation(HttpServletRequest request,Model model){    
			  	List<Relation> relations = this.relationService.getAllRelation(); 
		      //  model.addAttribute("relations", relations);  
		     //   return "showAllRelations"; 
			  	return relations;
		  } 
	      
	      @ResponseBody
		  @RequestMapping("/deleteRelationById")  
		  public String deleteRelationById(HttpServletRequest request,Model model){    
				int row = this.relationService.deleteRelationById(20); 
				//return "deleteRelationById";  
				return row+"";
		  } 
	      
	      @ResponseBody
		  @RequestMapping(value="/insertRelation",method=RequestMethod.POST)  
		  public String insertRelation(Relation relation){    
				int row = this.relationService.insertRelation(relation) ;
				//return "insertRelation"; 
				return row+"";
		  }
	      
	      @ResponseBody
		  @RequestMapping(value="/updateRelation",method=RequestMethod.POST)  
		  public String updateRelation(@ModelAttribute Relation relation){ 
			  	
				int row = this.relationService.updateRelationById(relation);
			//	return "updateRelation"; 
				return row+""; 
		  }
	      
	      /**
	  	 * 根据userId查询该用户所有的已投标信息(包括中标信息)，通过拼接成为json格式的字符串返回
	  	 * @author 肖梦雅
	  	 * @param request
	  	 * @return
	  	 */
	  	@RequestMapping(value="/getAllFkProjects",produces="application/json;charset=utf-8")
	  	@ResponseBody
	  	public Page<Judge> getAllFkProjects(HttpServletRequest request,int page,int rows){
	  		int userId=((User)request.getSession().getAttribute("user")).getUserId();
	  		User user=(User)request.getSession().getAttribute("user");
	  		Page<Project> projectPage=new Page<>();
	  		projectPage.setStart((page - 1) * rows + 1);
	  		projectPage.setEnd(page*rows);
	  		//把userId保存在total中，因为project中也有user对象，直接传userId或user识别会出错
	  		projectPage.setTotal(userId);
	  		//计算project的总条数
	  		int countTotal=relationService.getCountTotalNum(userId);
	  		
			List<Win> wins=new ArrayList<Win>();
			List<Project> projectList=projectService.getAllProjectsByUserIdPage(projectPage);
			List<Judge> judges=new ArrayList<>();
			for (int i=0; i<projectList.size();i++) {
				Judge judge=new Judge();
				judge.setProject(projectList.get(i));
	  			//查找是否中标
	  			wins=winService.getWinByByUserIdAndProjectId(userId,projectList.get(i).getProjectId());
	  			if(wins.size()>0){
	  				// 1---->中标 
	  				judge.setWinFlag(1);
	  			}
	  			else{ //0---->未中标
	  				judge.setWinFlag(0);
	  			}
	  			
	  			judges.add(judge);
			}
			Page<Judge> jPage=new Page<Judge>(); 
			jPage.setRows(judges);
			jPage.setTotal(countTotal);
	  		return jPage;
	  	}
	  	
	  	/**
	  	 * 根据项目id和用户id删除已投标的项目信息
	  	 * @author 肖梦雅
	  	 * @param request
	  	 */
	  	@RequestMapping("/deleteBidById")
	  	@ResponseBody
	  	public Integer deleteBidById(Integer projectId,HttpServletRequest request){
	  		User user=((User)request.getSession().getAttribute("user"));
	  		Project project=projectService.getProjectById(projectId);
	  		project.setUser(user);
	  		int i=relationService.delRelationByPidAndUseridService(project);
	  		if(i>0){
	  			return 1;
	  		}else{
	  			return 0;
	  		}
	  	}
		   
		
	  	/**
	  	 * 用户点击投标按钮，向gjzb_relation(投标信息表)表中插入数据
	  	 * @author 肖梦雅
	  	 * @param
	  	 * @return
	  	 */
	  	@RequestMapping("/addBid")
	  	@ResponseBody
	  	public int addBid(Relation relation){
	  		int i=relationService.insertRelation(relation);
	  		if(i>0){
	  			return 1;
	  		}else{
	  			return 0;
	  		}
	  	}
	  	
	  	
}
