package com.ugaoxin.history;
 
import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ugaoxin.bean.YunAdmin;
import com.ugaoxin.bean.YunHistory;
import com.ugaoxin.service.IYunHistoryService;
import com.ugaoxin.util.ShiroCommonUtil;
import com.ugaoxin.util.WebUtils;

/**
 * 
 * Copyright © Array老师. All rights reserved.
 * 
 * @Title: SysHistoryAop.java
 * @Prject: ugaoxin-yun
 * @Package: com.ugaoxin.history
 * @Description: insert message of dosomething
 * @author: Array老师
 * @version: V1.0
 */

@Aspect
@Component
public class SysHistoryAop {

	@Autowired
	private IYunHistoryService historySerivce;
    // 传统方式历史痕迹或者log的处理：本地日志的处理 
	private static final Logger logger=LoggerFactory.getLogger(SysHistoryAop.class);
	
	public SysHistoryAop(){
		System.out.println("===切面工具类Array");
	}
	//MVC层的切点  
	//controller切点                         com.ugaoxin.history
	@Pointcut("@annotation(com.ugaoxin.history.SysHistory)")
	public void controllerAop(){
		System.out.println("===切面工具类controllerAop  Array");
	}
	
	// 拦截controller的前置通知
	@Before("controllerAop()")
	public void doBefore(JoinPoint joinpoint){
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		 //HttpSession session = request.getSession();
		 //获取操作的用户，哪个用户留下的痕迹
		 YunAdmin yunAdmin = (YunAdmin) ShiroCommonUtil.getSubject().getPrincipal();
		 String requestURI = request.getRequestURI();
		 //ip
		 String ip = WebUtils.getRemoteAddr(request); 
		 // 操作痕迹方法
		 String method=joinpoint.getSignature().getDeclaringTypeName()+"."+joinpoint.getSignature().getName();
		 
		 String parms="";
		 if(joinpoint.getArgs()!=null&&joinpoint.getArgs().length>0){
			 for (int i = 0; i < joinpoint.getArgs().length; i++) {
				 parms+=joinpoint.getArgs()[i]+";";
						  
			} 
			 
		 }
		 
		 //引入操作痕迹的前置通知
		 try {
			String remark= getControllerMethodDescription(joinpoint);
			String userName=yunAdmin.getAdminName();
			YunHistory yun = new YunHistory();
			yun.setUpdateTime(new Date());
			yun.setRemark(remark);
			yun.setContent(remark);
			yun.setIp(ip);
			yun.setInStatus(parms);
			yun.setAdminName(userName); 
			yun.setDoHref(requestURI); 
			historySerivce.insert(yun);
			
		} catch (Exception e) {
			logger.info("切面点异常");
			e.printStackTrace();
		}
		 
		 
	}
	
	
	/*
	 * 操作方法的描述
	 */
	public  static String getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {    
        String targetName = joinPoint.getTarget().getClass().getName();    
        String methodName = joinPoint.getSignature().getName();    
        Object[] arguments = joinPoint.getArgs();    
        Class targetClass = Class.forName(targetName);    
        Method[] methods = targetClass.getMethods();    
        String description = "";    
         for (Method method : methods) {    
             if (method.getName().equals(methodName)) {    
                Class[] clazzs = method.getParameterTypes();    
                 if (clazzs.length == arguments.length) {    
                    description = method.getAnnotation(SysHistory.class).value();    
                     break;    
                }    
            }    
        }    
         return description;    
    }    
	
}
