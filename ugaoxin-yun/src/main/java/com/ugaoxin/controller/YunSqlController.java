package com.ugaoxin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.ugaoxin.bean.YunAdmin;
import com.ugaoxin.bean.YunRoles;
import com.ugaoxin.bean.YunUsers;
import com.ugaoxin.history.SysHistory;
import com.ugaoxin.service.IYunMenusService;
import com.ugaoxin.service.IYunRolesService;
import com.ugaoxin.service.IYunUserService;
import com.ugaoxin.util.IDGenerator;
import com.ugaoxin.util.ShiroCommonUtil;
import com.ugaoxin.util.YunResult;
import com.ugaoxin.vo.ArrayJsonBean;
import com.ugaoxin.vo.RoleMenuVO;


/**  
 * Copyright © Array老师. All rights reserved.
 * @Title: YunUserController.java
 * @Prject: ugaoxin-yun
 * @Package: com.ugaoxin.controller
 * @Description: TODO
 * @author: Array老师 
 * @version: V1.0  
 */
@Controller
@RequestMapping("yun/sql")
public class YunSqlController {
	 
	/**
	 * 数据sql监控
	 */
	@RequestMapping("/show")
	@RequiresPermissions("yun:sql:show")
	public String getSqlShow(){ //ModelAndView  
		return  "../druid/index.html";
	}
	 
}
