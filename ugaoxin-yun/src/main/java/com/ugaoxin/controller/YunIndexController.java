package com.ugaoxin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.stat.TableStat.Mode;
import com.ugaoxin.bean.YunAdmin;
import com.ugaoxin.util.ShiroCommonUtil;

/**  
 * Copyright © Array老师. All rights reserved.
 * @Title: YunIndexController.java
 * @Prject: ugaoxin-yun
 * @Package: com.ugaoxin.controller
 * @Description: TODO
 * @author: Array老师 
 * @version: V1.0  
 */
@Controller
@RequestMapping("/yun/index")
public class YunIndexController {

	@RequestMapping("index")
	public ModelAndView  yunIndex(){
		// 获取登录者信息 
		 YunAdmin yunAdmin = null;
		 yunAdmin = (YunAdmin) ShiroCommonUtil.getSubject().getPrincipal();
		  ModelAndView mv = new ModelAndView("redirect:../../index.jsp");
		   mv.addObject("info", yunAdmin);
		   return mv;
	}
}
