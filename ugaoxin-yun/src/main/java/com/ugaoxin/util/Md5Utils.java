package com.ugaoxin.util;

import org.apache.shiro.crypto.hash.Md5Hash;


 
/**  
 * Copyright © Array老师. All rights reserved.
 * @Title: Md5Utils.java
 * @Prject: ugaoxin-yun
 * @Package: com.ugaoxin.util
 * @Description: TODO
 * @author: Array老师 
 * @version: V1.0  
 */
public class Md5Utils {
	
	 
	private static final int ITERATIONS = 6;
	 
	private static final int SALT_NUMBER = 6;
	
	private Md5Utils() {
		throw new AssertionError();
	}
	 
	public static String getMd5(String password, String loginName, String salt) {
		return new Md5Hash(password, getCredentialsSalt(loginName, salt), ITERATIONS).toString();
	}
	public static String getMd5NotSalt(String password, String loginName) {
		return new Md5Hash(password, loginName, ITERATIONS).toString();
	}
	//证书凭证/
	public static String getCredentialsSalt(String loginName, String salt) {
		return loginName + salt;
	}
	
 
	public static String getSalt() {
		return YunRandomUtils.getString(SALT_NUMBER);
	}
}
