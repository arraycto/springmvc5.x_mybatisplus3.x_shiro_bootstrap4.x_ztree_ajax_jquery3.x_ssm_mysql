package com.ugaoxin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.ugaoxin.bean.YunHistory;
import com.ugaoxin.service.IYunHistoryService;
import com.ugaoxin.util.YunResult;
import com.ugaoxin.vo.ArrayJsonBean;



/**  
 * Copyright © Array老师. All rights reserved.
 * @Title: YunHistoryController.java
 * @Prject: ugaoxin-yun
 * @Package: com.ugaoxin.controller
 * @Description: 展示操作痕迹
 * @author: Array老师 
 * @version: V1.0  
 */
@Controller
@RequestMapping("yun/history")
public class YunHistoryController {
	
	
 
	// 引入操作痕迹对应的Service
	@Autowired
	private IYunHistoryService yunHistoryService;
	
	// 展示角色列表
	// 分析：
	// 传统：1.直接通过mv模式进行jsp跳转
	// 新颖：2.ajax和restful接口的微服务形式 ：a,跳转到jsp; b,在jsp界面通过ajax调用接口
	
	/**
	 * a,跳转到jsp
	 */
	@RequestMapping("/show")
	@RequiresPermissions("yun:history:show")
	public String getRolesShow(){ //ModelAndView  
		return "views/history/history-show";
	}
	
	/**
	 * b,在jsp界面通过ajax调用接口
	 * 两种数据解决方案：1.bt表格 bootstrap tables ；2.dataTables(推荐)
	 * datatables参数注意：draw,start,length,keyword,columns,order
	 */
	@RequestMapping("/showHistoryList")
	@RequiresPermissions("yun:history:show")
	@ResponseBody
	public YunResult showsHistoryList( Integer draw,Integer start,Integer length,String keyword,String columns,String order) {
		
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
				cName="id";        
			} else if(index==2) {
				cName="admin_name";
			} else if(index==3) {
				cName="content";
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
		 ew.like("admin_name", keyword).or().like("content", keyword).or().like("remark", keyword);
		 
		 //yunRolesService.selectPage(pagePlus,ew);
		 Page<YunHistory> page  = yunHistoryService.selectPage(pagePlus,ew);//mybatisplus2.x的用法，mybatisplus3.x的用法参考考试的另外甘特图系统实战
				 
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
	 
}
