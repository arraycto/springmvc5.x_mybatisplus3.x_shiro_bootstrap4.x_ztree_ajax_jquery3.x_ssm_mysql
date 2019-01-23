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
@RequestMapping("yun/user")
public class YunUserController {
	
	
	@Autowired
	private IYunMenusService yunMenusSerivce;
	
	// 引入对应的service层
	@Autowired
	private IYunRolesService yunRolesService;
	
	// 引入user对应的Service
	@Autowired
	private IYunUserService yunUserService;
	
	// 展示角色列表
	// 分析：
	// 传统：1.直接通过mv模式进行jsp跳转
	// 新颖：2.ajax和restful接口的微服务形式 ：a,跳转到jsp; b,在jsp界面通过ajax调用接口
	
	/**
	 * a,跳转到jsp
	 */
	@RequestMapping("/show")
	@RequiresPermissions("yun:user:show")
	public String getRolesShow(){ //ModelAndView  
		return "views/user/user-show";
	}
	
	/**
	 * b,在jsp界面通过ajax调用接口
	 * 两种数据解决方案：1.bt表格 bootstrap tables ；2.dataTables(推荐)
	 * datatables参数注意：draw,start,length,keyword,columns,order
	 */
	@RequestMapping("/showuUsersList")
	@RequiresPermissions("yun:user:show")
	@ResponseBody
	public YunResult showuUsersList( Integer draw,Integer start,Integer length,String keyword,String columns,String order) {
		
		String cName="";
		// 排序类型
		String orderType="";
		// 很多都是datatables 固定搭配  严格按照老师的节奏来。
		// 1.将参加排序的数组order字段，转换为json
		JsonParser jsonParser = new JsonParser();
		// String 转换成json数组
		JsonArray jsonArray = jsonParser.parse(order).getAsJsonArray();
		
		// 2.将所有字段进行转换
		JsonArray jsonColumnsArray = jsonParser.parse(columns).getAsJsonArray();
		
		
		// 3.将所有值存入list中
		List<ArrayJsonBean> orderList = new ArrayList<ArrayJsonBean>();  /// 自定义一个bean 按照datatables的规则来
		List<ArrayJsonBean> columnsList = new ArrayList<ArrayJsonBean>();
		
		Gson gson = new Gson();
		
		// 4.数组里面的值转换存入list
		for(JsonElement  orderJson:jsonArray) {
			
			ArrayJsonBean  orderBean = gson.fromJson(orderJson, ArrayJsonBean.class);
			orderList.add(orderBean);
		}
		
		for (ArrayJsonBean arrayJsonBean : orderList) {
			// 根据前台反馈的序号，找到排序对应数据库表中的具体字段名称
			Integer index = Integer.parseInt(arrayJsonBean.getColumn());
			if(index==1){
				cName="user_id";        
			} else if(index==2) {
				cName="user_name";
			} else if(index==3) {
				cName="real_name";
			}
			orderType= arrayJsonBean.getDir();
		}
		
		
		// 5.分页  mybatisplus     mybatisPlus 2.x  和 3.x   两种都不过时，企业目前最常用的是2.x ，Array老师的2.x和3.x都讲：甘特图，日程管理系统
		// Page page = new Page(0,10)
		 Page pagePlus = new Page(start/length+1,length);
		 pagePlus.setOrderByField(cName);
		 // 升序降序
		 Boolean  isAsc = false;
		 if(orderType.equals("desc")){
			 isAsc=true;
		 }
		 pagePlus.setAsc(isAsc);
		 
		 // 6.模糊查询
		 
		 EntityWrapper ew = new EntityWrapper();
		 ew.like("user_id", keyword).or().like("user_name", keyword).or().like("real_name", keyword);
		 
		 //yunRolesService.selectPage(pagePlus,ew);
		 Page<YunUsers> page  = yunUserService.selectPage(pagePlus,ew);//mybatisplus2.x的用法，mybatisplus3.x的用法参考考试的另外甘特图系统实战
				 
	      // 为了前台显示，我们必须按照以下格式输出
		 Map<String,Object> rmap = new HashMap<String,Object>();
		 rmap.put("draw", draw);
		 rmap.put("recordsFiltered", page.getTotal());
		 rmap.put("recordsTotal",    page.getTotal());
		 rmap.put("list", page.getRecords());
		 
		 YunResult yru = new YunResult();
		 yru.setData(rmap);
		 
		 
       /* for(JsonElement  columnsJson:jsonColumnsArray) {
			
			ArrayJsonBean  columnsBean = gson.fromJson(columnsJson, ArrayJsonBean.class);
			columnsList.add(columnsBean);
		}
		*/ 
		return yru;
		
	}
	
	 /*
	  * 添加或更新，普通用户，可以二合一   toAdd  toUpdate
	  * 零基础  举一反三  进阶
	  */
	
	@RequestMapping("saveOrUp")
	@RequiresPermissions("yun:user:save")
	 public ModelAndView addUser(@CookieValue("doType")String doType,@RequestParam(value="flag",defaultValue="0",required=false)String flag){
		 ModelAndView mv = new ModelAndView();
		 
		if(flag.equals("init")){
			doType="add";
		} else { 
			if(!doType.equals("add")){ 
				YunUsers yunUsers = yunUserService.selectById(doType);
				mv.addObject("user", yunUsers);
				
				mv.addObject("id", yunUsers.getId());
				
			} 
			
		}
		
		 mv.setViewName("views/user/user-saveOrUp");
		  
		return mv;
		
	}
	
	/*
	 * doAdd 实际插入数据到用户列表
	 */
	@SysHistory(value="添加或者插入普通用户")
	@RequestMapping("insertOrUpdateByUser")
	@ResponseBody
	public YunResult insertOrUpdate(YunUsers yunUsers){
		
		 
		 // 获取当前登陆者，因为我们需要或者更新者Id或者姓名
		 Long userId = ShiroCommonUtil.getUserId();
		
		 String userName = ShiroCommonUtil.getUserName();
		 yunUsers.setUserId(IDGenerator.getLongId()); 
		 boolean b =false;
		 
		 if(yunUsers.getId()!=null) {
				//更新操作
			     yunUsers.setUpdateUser(userName); 
			     b = yunUserService.insertOrUpdate(yunUsers);
			      
			 
			} else {
				//添加操作
				 yunUsers.setCreateUser(userName);
				 b= yunUserService.insertOrUpdate(yunUsers);
			}
		 
		 if(b){
			 new YunResult(1, "1");
		 }
		return new YunResult(0,"0");
		
	}
	
	@RequestMapping(value="/getRolesMenusByrId",produces = "application/json; charset=utf-8") 
	@ResponseBody
	public String getRolesMenusByrId(Long rId){
		
		// 查看角色对应的MenuId
		YunAdmin  yunAdmin = new  YunAdmin();
		yunAdmin.setRoleId(rId);
		List<RoleMenuVO> menusByAdmin = yunMenusSerivce.getMenusByrId(rId); 
		
		Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		
		 
		return gson.toJson(menusByAdmin);
		
	}
	
	
	/*
	 * 删除
	 */
	 @RequestMapping("/delUsersById/{rId}")
	 @ResponseBody
	public YunResult delRoleById(@PathVariable  Integer rId){
		
		boolean b = yunUserService.deleteById(rId);
		if(b==true) {
			return new YunResult(1, "成功删除！");
		} else {
			return new YunResult(0, "删除失败！");
		}
		 
		
	}
	 
	 /*
	  * 批量删除
	  */
	 @RequestMapping("/delUsersByIds/{rIds}")
	 @ResponseBody
	 public YunResult delRolesByIds(@PathVariable  String rIds){
		 if(rIds!=null||!rIds.equals("")) {
			 int count = 0;
			 // 对传递过来的id集合进行分割
			 String[] ids = rIds.split(",");
			 for (String id : ids) {
				 
				 yunUserService.deleteById(Integer.valueOf(id)); 
				 
				 count++;
			 }
			 if(count>0) {
				 return new YunResult(1, "批量删除成功！");
			 }
			
			 
			          
		 }
		 
		 return new YunResult(0, "删除失败！");
		 
		 
		 
	 }
}
