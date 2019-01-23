package com.ugaoxin.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.google.code.kaptcha.Constants;
import com.ugaoxin.bean.YunAdmin;
 
 
/**  
 * Copyright © Array老师. All rights reserved.
 * @Title: ShiroUtils.java
 * @Prject: ugaoxin_yun
 * @Package: com.ugaoxin.util
 * @Description: TODO
 * @author: Array老师 
 * @version: V1.0  
 */
public class ShiroCommonUtil {

	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	public static YunAdmin getUserEntity() {
		return (YunAdmin)SecurityUtils.getSubject().getPrincipal();
	}

	public static Long getUserId() {
		return getUserEntity().getId();
	}
	public static String getUserName() {
		return getUserEntity().getAdminName();
	}

	public static void setSessionAttribute(Object key, Object value) {
		getSession().setAttribute(key, value);
	}

	public static Object getSessionAttribute(Object key) {
		return getSession().getAttribute(key);
	}

	public static boolean isLogin() {
		return SecurityUtils.getSubject().getPrincipal() != null;
	}

	public static void logout() {
		SecurityUtils.getSubject().logout();
	}
	
	public static String getKaptcha(String key) {
		String kaptcha = getSessionAttribute(key).toString();
		getSession().removeAttribute(key);
		return kaptcha;
	}

	
	/**
	 * 验证验证码
	 * @param userInputCaptcha
	 * @return 正确:true/错误:false
	 */
	public static boolean validate(String registerCode) {
		// 获取Session中验证码
		Object captcha = ServletUtils.getAttribute("googleCode");
		if (StringUtils.isEmpty(registerCode)) {
			return false;
		}
		boolean result = registerCode.equalsIgnoreCase(captcha.toString());
		if (result) {
			ServletUtils.getRequest().getSession().removeAttribute("googleCode");
		}
		return result;
	}
}
