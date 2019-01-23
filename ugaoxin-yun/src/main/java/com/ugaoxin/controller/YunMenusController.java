package com.ugaoxin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ugaoxin.bean.YunAdmin;
import com.ugaoxin.service.IYunMenusService;
import com.ugaoxin.util.ShiroCommonUtil;
import com.ugaoxin.vo.YunMenusShow;

/**  
 * Copyright © Array老师. All rights reserved.
 * @Title: YunMenusController.java
 * @Prject: ugaoxin-yun
 * @Package: com.ugaoxin.controller
 * @Description: aside.jsp的菜单获取
 * @author: Array老师 
 * @version: V1.0  
 */
@Controller
@RequestMapping("/yun/left")
public class YunMenusController {

	@Resource
	private IYunMenusService yunMenusSerivce;
	
	
	/**
	 * 根据登录者的权限不同，获取左侧的菜单不同，通过权限控制。
	 * 核心模块的讲解，提供大家举一反三
	 */
	@RequestMapping("/getLeftMenus")
	@ResponseBody
	public List<YunMenusShow> getLeftMenus() {
		
		//获取当前登录者的权限和相关信息
		YunAdmin yunAdmin = (YunAdmin) ShiroCommonUtil.getSubject().getPrincipal();
		List<YunMenusShow> list  = null;
		if(yunAdmin!=null) {
			list = yunMenusSerivce.getLeftMenusByAdmin(yunAdmin);
		}
		return list;
		
	}
}
