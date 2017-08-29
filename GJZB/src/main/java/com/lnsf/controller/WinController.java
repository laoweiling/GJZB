package com.lnsf.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lnsf.entity.Project;
import com.lnsf.entity.Relation;
import com.lnsf.entity.ShowRelationMap;
import com.lnsf.entity.User;
import com.lnsf.entity.Win;
import com.lnsf.service.RelationService;
import com.lnsf.service.UserService;
import com.lnsf.service.WinService;
import com.lnsf.service.impl.RelationServiceImpl;
import com.lnsf.service.impl.WinServiceImpl;
import com.lnsf.util.RC4Encrypt;

/**
 * 
 * @author 劳伟玲
 * @version 时间：2017-8-1 18:20:43
 * @decribe:win表的controller层
 *
 */
@Controller
@RequestMapping("/Win")
public class WinController {
	
		 
	      @Autowired
		  private WinService winService; 
		  
		  @ResponseBody
		  @RequestMapping("/showWinById")  
		  public Win showWinById(HttpServletRequest request,Model model){ 	       
		        Win win = this.winService.getWinByWinId(2); 
		      //  model.addAttribute("win", win);  
		      //  return "showWinById";  
		        return win;
		  } 
	    
		  @ResponseBody
		  @RequestMapping("/showAllWins")  
		  public List<Win> showAllRelation(HttpServletRequest request,Model model){    
			  	List<Win> wins = this.winService.getAllWin(); 
		      //  model.addAttribute("wins", wins);  
		     //   return "showAllWins"; 
			  	return wins;
		  } 
		  @ResponseBody
		  @RequestMapping("/deleteWinById")  
		  public int deleteWinById(HttpServletRequest request,Integer winId){   
			  
				int row = this.winService.deleteWinById(winId);
			//	return "deleteWinById"; 
				return row;
		  } 
		  @ResponseBody
		  @RequestMapping(value="/insertWin",method=RequestMethod.POST)  
		  public int insertRelation(Win win){    
				int row = this.winService.addWin(win) ;
				//return "insertwin";  
				return row;
		  }
		  @ResponseBody
		  @RequestMapping(value="/updateWin",method=RequestMethod.POST)  
		  public int updateRelation(@ModelAttribute Win win){ 
			  	
				int row = this.winService.updateWinByProjectId(win);
				//return "updateRelation";  
				return row;
		  }
		  /**
		   * 
		   * @author 劳伟玲
		   * @version 时间：2017-8-1 18:20:43
		   * @describe:根据指定用户查找个人所有中标项目
		   * User user 是从登录那边获取到用户,测试时先别添加user参数
		   */
		  
		  @ResponseBody
		  @RequestMapping("/getAllProjectInWinByUser")  
		  //public List<Win> getAllProjectInWinByUser(User user){ 
		  public List<Win> getAllProjectInWinByUser(HttpServletRequest request){
			  User user1 = (User)request.getSession().getAttribute("user");
			  List<Win> winsByUser = winService.getAllProjectInWinByUser(user1);
			  /*List<Win>  winsByUser = winService.getAllProjectInWinByUser();*/
		      
		       return winsByUser;
		  } 
		  
		  /**
		   * 
		   * @author 劳伟玲
		   * @version 时间：2017-8-1 18:20:43
		   * @describe:根据指定用户查找个人所有未中标项目
		   * User user 是从登录那边获取到用户,测试时先别添加user参数
		   */
		  @ResponseBody
		  @RequestMapping("/getAllProjectNotInWinByUser")  
		  public List<ShowRelationMap> getAllProjectNotInWinByUser(HttpServletRequest request){
			  User user1 =(User)request.getSession().getAttribute("user");
			  List<ShowRelationMap> projectsByUser = winService.getAllProjectNotInWinByUser(user1);
			  //List<ShowRelationMap> projectsByUser = winService.getAllProjectNotInWinByUser();
		      
		       return projectsByUser;
		  } 
		  
		
}
