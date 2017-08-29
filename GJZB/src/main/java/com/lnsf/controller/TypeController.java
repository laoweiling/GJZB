package com.lnsf.controller;

import java.util.List;

//import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.lnsf.entity.Type;
import com.lnsf.service.TypeService;

/**
 * @author黄浩贡
 * @version 创建时间：22017年7月28日21:23:30
 * @introduction 创建type的增删改查操作
 *
 */
@Controller
@RequestMapping("type")
public class TypeController {

	@Autowired
	private TypeService typeService;
	
	
	
	/**
	 * 投标模块中的展示项目类型(info.jsp页面)，返回JSON格式的字符串
	 * @author  肖梦雅
	 * @version 创建时间：22017年7月31日19:42:32
	 * @return
	 */
	@RequestMapping(value="/getAllTypes",produces="application/json;charset=utf-8")
	@ResponseBody
	public String  getAllTypes(){
		Gson gson=new Gson();
		List<Type> types = typeService.getTypes();		
		String typeString=gson.toJson(types);
		return typeString;
	}
	
	
	
	//这里每个@RequestMapping的值都加上“/test/”的原因是我的测试页面在test文件夹下
	/**
	 * @author  组合测试人：黄卉
	 * @version 创建时间：22017年7月28日21:23:30
	 * @introduction 创建type的增删改查操作
	 *测试状态：通过测试
	 */
	//查找所有类型
	@ResponseBody
	@RequestMapping("/test/getTypes")
	//测试网址：http://localhost:8888/GJZB//test/getTypes
	public List<Type> getTypes(){
		List<Type> type = typeService.getTypes();		
		return type;
	}
	
	
}
