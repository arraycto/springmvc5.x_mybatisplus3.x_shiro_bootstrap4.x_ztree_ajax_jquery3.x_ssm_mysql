package com.ugaoxin.controller;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.bouncycastle.crypto.Digest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
import com.ugaoxin.enums.SexEnum;
import com.ugaoxin.history.SysHistory;
import com.ugaoxin.service.IYunAdminService;
import com.ugaoxin.service.IYunMenusService;
import com.ugaoxin.service.IYunRolesService;
import com.ugaoxin.util.Md5Utils;
import com.ugaoxin.util.ShiroCommonUtil;
import com.ugaoxin.util.YunResult;
import com.ugaoxin.vo.ArrayJsonBean;
import com.ugaoxin.vo.RoleMenuVO;


/**  
 * Copyright © Array老师. All rights reserved.
 * @Title: YunAdminController.java
 * @Prject: ugaoxin-yun
 * @Package: com.ugaoxin.controller
 * @Description: 云平台的管理员管理 ，依托于零基础阶段的角色管理模块
 * @author: Array老师 
 * @version: V1.0  
 */
@Controller
@RequestMapping("yun/admin")
public class YunAdminController {
	
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    dateFormat.setLenient(false);  
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
	    
	     /* // 处理枚举类型
	 		binder.registerCustomEditor(SexEnum.class, new PropertyEditorSupport() {
	 		    public void setAsText(String value) {
	 		        try {
	 		            setValue(SexEnum.parseGender(value));
	 		        } catch(IllegalArgumentException e) {
	 		            setValue(Gender.FEMALE);
	 		        } 
	 		    }
	 		    public String getAsText() {
	 		        return ((SexEnum)getValue()).getValue();
	 		    }        
	 		});*/
	}
	
	 
	
	 @Autowired
	private IYunMenusService yunMenusSerivce;
	
	// 引入对应的service层
	@Autowired
	private IYunRolesService yunRolesService;
	
	
	@Autowired
	private  IYunAdminService yunAdminService;
	
	// 展示角色列表
	// 分析：
	// 传统：1.直接通过mv模式进行jsp跳转
	// 新颖：2.ajax和restful接口的微服务形式 ：a,跳转到jsp; b,在jsp界面通过ajax调用接口
	
	/**
	 * a,跳转到管理员的jsp
	 */
	@RequestMapping("/show")
	@RequiresPermissions("yun:admin:show")
	public String getRolesShow(){ //ModelAndView  
		return "views/admin/admin-show";
	}
	
	/**
	 * b,在jsp界面通过ajax调用接口
	 * 两种数据解决方案：1.bt表格 bootstrap tables ；2.dataTables(推荐)
	 * datatables参数注意：draw,start,length,keyword,columns,order
	 */
	@RequestMapping("/showAdminList")
	@RequiresPermissions("yun:admin:show")
	@ResponseBody
	public YunResult getAdminList( Integer draw,Integer start,Integer length,String keyword,String columns,String order) {
		
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
				cName="real_name";
			} else if(index==4) {
				cName="email";
			}else if(index==5) {
				cName="sex";
			}else if(index==6) {
				cName="telephone";
			}
			orderType= arrayJsonBean.getDir();
		}
		
		
		// 5.分页  mybatisplus
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
		 ew.like("admin_name", keyword).or().like("real_name", keyword).or().like("email", keyword).like("telephone", keyword);
		 
		 
		 Page<YunAdmin> page  = yunAdminService.selectPage(pagePlus,ew);
				 
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
	  * 添加或更新，可以二合一   toAdd  toUpdate
	  * 
	  */
	@RequestMapping("saveOrUp")
	@RequiresPermissions("yun:admin:save")
	 public ModelAndView addYunRole(@CookieValue("adminDoType")String id){   //   roles  =  {rid:1;roleName:"超级管理";......}
		 ModelAndView mv = new ModelAndView();
		 Gson gson = new Gson();
		 if(!id.equals("add")){
			  // roles 传递的是每一个角色的详细信息，json的字符串
			 // 修改页面，需要回显角色信息 
			   // 1传递对象的方式，需要处理时区的问题和不安全的问题
			   //YunRoles upRole = gson.fromJson(roles, YunRoles.class);
			 
			 // 2.传递Id的方式，先查询，后回显
			 
			 YunAdmin yunAdmin = yunAdminService.selectById(id);
			 String adminPassword = yunAdmin.getAdminPassword();
			 // 解密 
			 yunAdmin.setAdminPassword("");
			 mv.addObject("yunAdmin", yunAdmin);
			 
			 mv.addObject("id", yunAdmin.getId());
			 
		 } 
		 List<YunRoles> yunRoles = yunRolesService.selectList(null);//查询所有菜单
		 
		 mv.setViewName("views/admin/admin-saveOrUp");
		 mv.addObject("yunRoles", yunRoles);
		
		return mv;
		
	}
	
	/*
	 * doAdd 实际插入数据到角色列表或者更新@RequestParam(value="yunAdmin",required=false)
	 * http://127.0.0.1:8082/yun/admin/doAdmin?adminName=2221&realName=221&email=221&sex=1&telephone=2221&roleId=2&_=1538900776497
	 */
	
	@RequestMapping("doAdmin")
	@ResponseBody
	public YunResult doAdmin(Long id,String adminName ,String adminPassword ,String realName,String email,String sex,String telephone,Long roleId){
		YunAdmin yunAdmin = new YunAdmin();
		yunAdmin.setId(id);
		yunAdmin.setAdminName(adminName);
		yunAdmin.setRealName(realName);
		yunAdmin.setEmail(email);
		if(sex!=null||!sex.equals("")){
		yunAdmin.setSex(SexEnum.valueOfCode(Integer.valueOf(sex)));
		}
		yunAdmin.setTelephone(telephone);
		yunAdmin.setRoleId(roleId);
		if(adminPassword!=null||!adminPassword.equals("")) {
			
			//密码的单独处理  拓展，可以加入盐进行二次验证 
			//yunAdmin.setAdminPassword(Md5Utils.getMd5(adminPassword, adminName, ""));
			// 另外一种加密方式，spring-core 自带的md5加密
			yunAdmin.setAdminPassword(DigestUtils.md5DigestAsHex(adminPassword.getBytes()));
		} 
		// 获取当前登陆者，因为我们需要或者更新者Id或者姓名
		 Long userId = ShiroCommonUtil.getUserId();
		
		 String userName = ShiroCommonUtil.getUserName();
		 yunAdmin.setRemark(userName);
		 Integer count = 0;
		  
		 
		 if(yunAdmin.getId()!=null) {
				//更新操作
			    count = yunAdminService.doUpdateAdmin(yunAdmin);
			 
			} else {
				//添加操作
				 count = yunAdminService.doAdmin(yunAdmin);
			}
		 
		 if(count>0){
			 return new YunResult(1, count+"");
		 }
		return new YunResult(0, count+"");
		
	}
	
	@RequestMapping(value="/getAdminMenusByrId",produces = "application/json; charset=utf-8") 
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
	 * 管理员的删除
	 */
	 @SysHistory(value="理员的删除")
	 @RequestMapping("/delAdminById/{id}")
	 @ResponseBody
	public YunResult delRoleById(@PathVariable  Long id){
		
		boolean b = yunAdminService.deleteById(id);
		if(b==true) {
			return new YunResult(1, "成功删除！");
		} else {
			return new YunResult(0, "删除失败！");
		}
		 
		
	}
	 
	 /*
	  * 批量删除
	  */
	 @RequestMapping("/delAdminByIds/{ids}")
	 @ResponseBody
	 public YunResult delRolesByIds(@PathVariable  String ids){
		 if(ids!=null||!ids.equals("")) {
			 int count = 0;
			 // 对传递过来的id集合进行分割
			 String[] adminids = ids.split(",");
			 for (String id : adminids) {
				 
				 yunAdminService.deleteById(Long.valueOf(id)); 
				 
				 count++;
			 }
			 if(count>0) {
				 return new YunResult(1, "批量删除成功！");
			 }
			
			 
			          
		 }
		 
		 return new YunResult(0, "删除失败！");
		 
		 
		 
	 }
}
