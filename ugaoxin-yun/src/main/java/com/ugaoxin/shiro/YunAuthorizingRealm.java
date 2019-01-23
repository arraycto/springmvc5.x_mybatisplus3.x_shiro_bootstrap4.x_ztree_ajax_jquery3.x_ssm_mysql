package com.ugaoxin.shiro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;





import com.ugaoxin.bean.YunAdmin;
import com.ugaoxin.bean.YunMenus;
import com.ugaoxin.dao.YunAdminMapper;
import com.ugaoxin.dao.YunMenusMapper;

/**  
 * Copyright © Array老师. All rights reserved.
 * @Title: YunAuthorizingRealm.java
 * @Prject: ugaoxin-yun
 * @Package: com.ugaoxin.shiro
 * @Description: TODO
 * @author: Array老师 
 * @version: V1.0  
 */
public class YunAuthorizingRealm  extends AuthorizingRealm {
	
	// shiro权限控制器
	@Autowired
	private YunAdminMapper yunAdminMapper;
	@Autowired
	private YunMenusMapper   yunMenusMapper;
	
	//为了方便调试，我们可以再类中引入log
	private  static Logger loger = LoggerFactory.getLogger(YunAuthorizingRealm.class);
	
	// 设置好调试的日志
	public YunAuthorizingRealm(){
		loger.info("你已经进入shiro进行权限的判断！");
	}
	
	public String getName() {
		return "yunAuthorizingRealm";
	}
	
	//获取云平台系统权限的具体方式方法，便于后面直接使用
	
	protected   AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection){
		//shiro 把用户名，密码，角色，菜单都可以存入PrincipalCollection集合中，供后面使用中直接获取
		    YunAdmin  yunAdmin = (YunAdmin) principalCollection.getPrimaryPrincipal();//获取登录用户
		    
		   //通过角色r_id去查询左侧显示的菜单
		   // 1.获取当前登录用户的r_id
		    Long rId = yunAdmin.getRoleId();
		    
		   // 2.通过r_id去数据库查询，该用户的权限和显示菜单
		    List<YunMenus> mList = yunMenusMapper.getMenusListByRoleId(rId);
	        
		    //把获取的权限添加到List保存
		    List<String> authorizationList = new ArrayList<String>();
		    
		    for (YunMenus yunMenus : mList) {
				if(yunMenus.getMenuAuthorization()!=null&&!"".equals(yunMenus.getMenuAuthorization())) {
					authorizationList.add(yunMenus.getMenuAuthorization());
				}
			}
			 
		    //获取用户的权限列表
		    Set<String> authorizationSet = new  HashSet<String>(); 
		    
		    for (String author : authorizationList) {
		    	if(StringUtils.isBlank(author)) {
		    		continue;
		    	}
		    	authorizationSet.addAll(Arrays.asList(author.trim().split(",")));
			}
		     
		    // 存入到shiro的对象中去
		    SimpleAuthorizationInfo   sa = new SimpleAuthorizationInfo();
		    sa.setStringPermissions(authorizationSet);
		    
			return sa;
		   
		
	}
	
	
	
	/*
	 * 获取用户相关信息和处理
	 */
	protected   AuthenticationInfo  doGetAuthenticationInfo(AuthenticationToken token) {
		
		// 查询用户信息
		 String  loginName = (String) token.getPrincipal();
		// 用户密码
		 String loginPassWord = new String((char[])token.getCredentials());
		 
		 // 查询用户的其他信息,查询用户是否存在
		 YunAdmin yunAdmin = new YunAdmin();
		 yunAdmin.setAdminName(loginName);
		 //yunAdmin.setAdminPassword(loginPassWord);
		 
		 // 为了方便直接使用mybatisplus的查询方式,如果使用的是mybatis那么此处的方法需要自己定义和研发
		 YunAdmin admin = yunAdminMapper.selectOne(yunAdmin);
		 if(admin==null) {
			  throw new UnknownAccountException("账号不存在！");
			  
		 }
		 //验证密码 ，运用MD5转换之后验证
		 loginPassWord = new Md5Hash(loginPassWord).toString();
		 
		 if(!loginPassWord.equals(admin.getAdminPassword())) {
			 throw new UnknownAccountException("密码错误！");
		 } //鉴权
		 // 特殊情况，有的使用户是初始用户，无权限也不让登录
		 if(admin.getRoleId()==null||admin.getRoleId()==0||"".equals(admin.getRoleId())){
			 throw new UnknownAccountException("该账户未分配给具体的角色权限，请联系管理员！");
		 }
		  SimpleAuthenticationInfo sac = new SimpleAuthenticationInfo(admin,loginPassWord,getName());
		return sac;
		 
	}
	
}
