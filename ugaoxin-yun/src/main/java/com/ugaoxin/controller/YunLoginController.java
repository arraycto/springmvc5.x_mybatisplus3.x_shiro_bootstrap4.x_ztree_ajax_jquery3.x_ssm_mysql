package com.ugaoxin.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Producer;
import com.ugaoxin.util.ShiroCommonUtil;
import com.ugaoxin.util.YunResult;

/**  
 * Copyright © Array老师. All rights reserved.
 * @Title: YunLoginController.java
 * @Prject: ugaoxin-yun
 * @Package: com.ugaoxin.controller
 * @Description: 登录相关的参数配置
 * @author: Array老师 
 * @version: V1.0  
 */
@Controller
@RequestMapping("/yun/login")
public class YunLoginController {

	@Autowired
	private Producer   producer;
	
	/**
	 * 验证码的获取
	 * 
	 */
	@RequestMapping("/getGooglCode")
	public void getGoogleCode(HttpServletRequest request,HttpServletResponse response) {
		
		// 引入Google验证码的插件
		String text = producer.createText(); 
		// 把创建的文本传入image中并且前台显示
		BufferedImage image = producer.createImage(text);
		// 把text保存到shiro中，同时image通过io流的形式传递给前台
		ShiroCommonUtil.setSessionAttribute("googleCode", text);
		try {
			// 格式自己的定义JPEG,jpg,png
			ImageIO.write(image, "jpg", response.getOutputStream());
			
		} catch (IOException e) {
			 
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/plogin")
	public String pLogin(Model model) {
		return "../plogin";
	}
	/*
	 * 后台登录, 封装 结果面向接口编程，封装成适合前台调用的微服务接口。 code ，message，
	 */
	@PostMapping("/login")
	@ResponseBody
	public YunResult login(HttpServletRequest request,String adminName,String adminPassword,String googleCode){
		
		 
		if (!ShiroCommonUtil.validate(googleCode)) {
			return new YunResult(-1, "验证码错误", ""); 
		}
		
		// 1.判断各个参数是否传递空值
		if(StringUtils.isEmpty(adminName)||StringUtils.isEmpty(adminPassword)||StringUtils.isEmpty(googleCode)){
			
			return  new YunResult(-1, "参数里面存在空值，请检查后重试", ""); 
			
		}
		try {
			    
			//2.通过shiro检查用户名等相关信息
			Subject subject = ShiroCommonUtil.getSubject();
			//3.获取token
			UsernamePasswordToken  adminToken =  new UsernamePasswordToken(adminName, adminPassword);
			subject.login(adminToken);
			
			// 拓展，可以验证salt的正确
		} catch(UnknownAccountException e) {
			return new YunResult(0, e.getMessage());
		} catch(AuthenticationException e){
		 
			return new YunResult(0, e.getMessage());
		 
		} // 学员自己根据情况看自定义自己的业务场景
		
		
		return new YunResult(1, "登录成功！"); 

		
	}
}
