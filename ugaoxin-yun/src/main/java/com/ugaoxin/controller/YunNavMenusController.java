package com.ugaoxin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ugaoxin.bean.YunMenus;
import com.ugaoxin.bean.YunRoles;
import com.ugaoxin.service.IYunMenusService;
import com.ugaoxin.service.IYunNavMenusService;
import com.ugaoxin.service.IYunRolesService;
import com.ugaoxin.util.ShiroCommonUtil;
import com.ugaoxin.util.YunResult;
import com.ugaoxin.vo.ArrayJsonBean;
import com.ugaoxin.vo.RoleMenuVO;
import com.ugaoxin.vo.YunMenusShow;
 

/**  
 * Copyright © Array老师. All rights reserved.
 * @Title: YunNavMenusController.java
 * @Prject: ugaoxin-yun
 * @Package: com.ugaoxin.controller
 * @Description: TODO
 * @author: Array老师 
 * @version: V1.0  
 */
@Controller
@RequestMapping("yun/nav")
public class YunNavMenusController {
	
	
	@Autowired
	private IYunMenusService yunMenusSerivce;
	
	// 引入对应的service层
	@Autowired
	private IYunRolesService yunRolesService;
	
	
	@Autowired
	private IYunNavMenusService yunNavMenusService;
	
	
	// 展示角色列表
	// 分析：
	// 传统：1.直接通过mv模式进行jsp跳转
	// 新颖：2.ajax和restful接口的微服务形式 ：a,跳转到jsp; b,在jsp界面通过ajax调用接口
	
	/**
	 * a,跳转到jsp
	 */
	@RequestMapping("/show")
	@RequiresPermissions("yun:nav:show")
	public String getRolesShow(){ //ModelAndView  
		return "views/nav/nav-show-list";
	}
	
	
	
	/**   dataTables方式的接口
	 * b,在jsp界面通过ajax调用接口   粗粒度还是细粒度
	 * 两种数据解决方案：1.bt表格 bootstrap tables ；2.dataTables(推荐)
	 * datatables参数注意：draw,start,length,keyword,columns,order
	 */
	@RequestMapping("/showNavList2")
	@RequiresPermissions("yun:nav:show")
	@ResponseBody
	public YunResult getNavList2( Integer draw,Integer start,Integer length,String keyword,String columns,String order) {
		
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
				cName="m_id";        
			} else if(index==2) {
				cName="menu_name";
			} else if(index==3) {
				cName="menu_icon";
			}else if(index==4) {
				cName="menu_url";
			}else if(index==5) {
				cName="menu_authorization";
			}
			orderType= arrayJsonBean.getDir();
		}
		
		
		// 5.分页  mybatisplus
		// Page page = new Page(0,10)
		 Page<YunMenus> pagePlus = new Page<YunMenus>(start/length+1,length);
		 pagePlus.setOrderByField(cName);
		 // 升序降序
		 Boolean  isAsc = false;
		 if(orderType.equals("desc")){
			 isAsc=true;
		 }
		 pagePlus.setAsc(isAsc);
		 
		 // 6.模糊查询
		 
		 EntityWrapper<YunMenus> ew = new EntityWrapper<YunMenus>();
		 ew.like("menu_name", keyword).or().like("menu_url", keyword).or().like("menu_authorization", keyword);
		 
		 
		 Page<YunMenus> page  = yunNavMenusService.selectPage(pagePlus,ew);
				 
		  
	      // 为了前台显示，我们必须按照以下格式输出
		 Map<String,Object> rmap = new HashMap<String,Object>();
		 rmap.put("draw", draw);
		 rmap.put("recordsFiltered", page.getTotal());
		 rmap.put("recordsTotal",    page.getTotal());
		 rmap.put("list", page.getRecords());
		 
		 YunResult yru = new YunResult();
		 yru.setData(rmap);
		 
		return yru;
		
	}
	
	 /*
	  * 添加或更新，可以二合一   toAdd  toUpdate
	  * 
	  */
	@RequestMapping("saveOrUp")
	@RequiresPermissions("yun:roles:save")
	 public ModelAndView addYunNav(@CookieValue("navDoType")String navDoType){   //   roles  =  {rid:1;roleName:"超级管理";......}
		 ModelAndView mv = new ModelAndView();
		 Gson gson = new Gson();
		 if(!navDoType.equals("add")){
			  
			 // 2.传递Id的方式，先查询，后回显
			 
			 String[] split = navDoType.split(",");
			 
			 YunMenus selectById = yunMenusSerivce.selectById(split[0]);
			 
			 mv.addObject("nav", selectById);
			 
			 mv.addObject("mId", selectById.getmId());
			 
		 }
		 
		 
		 mv.setViewName("views/nav/nav-saveOrUp"); 
		
		return mv;
		
	}
	
	
	/*
	  * 添加或更新，可以二合一   toAdd  toUpdate
	  * 
	  */
	@RequestMapping("childrenSaveOrUp")
	@RequiresPermissions("yun:roles:save")
	 public ModelAndView childSaveOrUp(@CookieValue("pid")String pid,@CookieValue("doType")String doType){   
		 ModelAndView mv = new ModelAndView();
		 Gson gson = new Gson();
		  if(!doType.equals("add")){
			  
			 // 2.传递Id的方式，先查询，后回显
			 //查询父目录的名称
			 
			 
			 YunMenus selectList = yunMenusSerivce.selectById(doType);
			 YunMenus yunMenus = yunMenusSerivce.selectById(selectList.getParentId());
			 mv.addObject("parentId", yunMenus.getmId());
			 mv.addObject("pName", yunMenus.getMenuName());
			 
			 
			 
			 mv.addObject("nav", selectList);
			 
			 mv.addObject("mId", selectList.getmId());
			 
		 } else {
			 
			 YunMenus yunMenus = yunMenusSerivce.selectById(pid);
			 mv.addObject("parentId", yunMenus.getmId());
			 mv.addObject("pName", yunMenus.getMenuName());
		 }
		 
		 
		 mv.setViewName("views/nav/nav-children-saveOrUp"); 
		
		return mv;
		
	}
	
	/**
	 *  bootstrap tables 方式的接口 
	 *  @return
	 */
	@RequestMapping(value="/showNavList",produces="text/html;charset=UTF-8")
	@RequiresPermissions("yun:nav:show")
	@ResponseBody
	public String getNavList(@RequestParam(value="pageNumber",defaultValue="1")Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue="10")Integer pageSize,Integer length,
			@RequestParam(value="search",required=false)String search,String order){
		
		// 采用和datatable接口类似的查询方式：mybatisplus
		Page pagePlus = new Page(pageNumber,10);
		
		//模糊查询
		EntityWrapper<YunMenus>  ew = new EntityWrapper<YunMenus>();
		ew.like("menu_name", search).or().like("menu_url", search);
		
		List<YunMenus> yList = yunNavMenusService.selectList(ew);
		
		// 按照bootstrap tables的识别格式去封装数据
		Map<String,Object> rmap = new HashMap<String, Object>();
		
		rmap.put("rows", yList);
		 
		// 处理日期类型的字段
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		
		 
		// 两种方式传递前台
		//1.map的方式     2.Gson的方式，如果采用Gson的方式，那么可以不用rmap.put("rows", yList);
		return gson.toJson(yList);
		
	}
	
	
	
	
	
	
	/*
	 * doAdd 实际插入数据到角色列表
	 */
	
	@RequestMapping("doAdd")
	@ResponseBody
	public YunResult doAdd(YunMenus yunMenus,@RequestParam(value="menusId",defaultValue="0")Long menusId){
		
		 
		// 获取当前登陆者，因为我们需要或者更新者Id或者姓名
		 Long userId = ShiroCommonUtil.getUserId();
		
		 String userName = ShiroCommonUtil.getUserName();
		
		 Integer count = 0; 
		 
		 //如果不是目录，需要有曾经关系
		 if(yunMenus.getParentId()!=null||!yunMenus.getParentId().equals("")){
			 menusId=yunMenus.getParentId();
		 }
		 
		 if(yunMenus.getmId()!=null) {
			 yunMenus.setUpdateUser(userName);
				//更新操作
			     count = yunNavMenusService.doUpdate(yunMenus,menusId);
			 
			} else {
				 yunMenus.setCreateUser(userName);
				//添加操作
				 count = yunNavMenusService.doAdd(yunMenus,menusId);
			}
		 
		 if(count>0){
			 new YunResult(1, count+"");
		 }
		return new YunResult(0, count+"");
		
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
	 * 角色的删除
	 */
	 @RequestMapping("/delRoleById/{rId}")
	 @ResponseBody
	public YunResult delRoleById(@PathVariable  Long rId){
		
		boolean b = yunRolesService.deleteById(rId);
		if(b==true) {
			return new YunResult(1, "成功删除！");
		} else {
			return new YunResult(0, "删除失败！");
		}
		 
		
	}
	 
	 /*
	  * 批量删除
	  */
	 @RequestMapping("/delRolesByIds/{rIds}")
	 @ResponseBody
	 public YunResult delRolesByIds(@PathVariable  String rIds){
		 if(rIds!=null||!rIds.equals("")) {
			 int count = 0;
			 // 对传递过来的id集合进行分割
			 String[] ids = rIds.split(",");
			 for (String id : ids) {
				 
				 yunRolesService.deleteById(Long.valueOf(id)); 
				 
				 count++;
			 }
			 if(count>0) {
				 return new YunResult(1, "批量删除成功！");
			 }
			
			 
			          
		 }
		 
		 return new YunResult(0, "删除失败！");
		 
		 
		 
	 }
}
