package com.ugaoxin.shiro;

import java.io.Serializable;

public class AuthorizingUser implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String  adminName;
	private String realName;
	private String salt;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public  String getCredentialsSalt(){
		if(adminName!=null&&salt!=null) {
			return adminName+salt;
		}
		return null; 
	}
	
}
