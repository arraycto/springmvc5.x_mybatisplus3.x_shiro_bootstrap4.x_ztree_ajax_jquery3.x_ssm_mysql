package com.ugaoxin.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**  
 * Copyright © Array老师. All rights reserved.
 * @Title: YunMenusShow.java
 * @Prject: ugaoxin-yun
 * @Package: com.ugaoxin.vo
 * @Description: 父子节点等关系展现
 * @author: Array老师 
 * @version: V1.0  
 */
public class YunMenusShow implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	    private Long mId;

	    private String menuName;

	    private String menuIcon;

	    private String menuUrl;

	    private String menuAuthorization;

	    private String isOn;

	    private Long parentId;

	    private String createUser;

	    private Date createTime;

	    private String updateUser;

	    private Date updateTime;
	    
	    private List<YunMenusShow> children;

		public Long getmId() {
			return mId;
		}

		public void setmId(Long mId) {
			this.mId = mId;
		}

		public String getMenuName() {
			return menuName;
		}

		public void setMenuName(String menuName) {
			this.menuName = menuName;
		}

		public String getMenuIcon() {
			return menuIcon;
		}

		public void setMenuIcon(String menuIcon) {
			this.menuIcon = menuIcon;
		}

		public String getMenuUrl() {
			return menuUrl;
		}

		public void setMenuUrl(String menuUrl) {
			this.menuUrl = menuUrl;
		}

		public String getMenuAuthorization() {
			return menuAuthorization;
		}

		public void setMenuAuthorization(String menuAuthorization) {
			this.menuAuthorization = menuAuthorization;
		}

		public String getIsOn() {
			return isOn;
		}

		public void setIsOn(String isOn) {
			this.isOn = isOn;
		}

		public Long getParentId() {
			return parentId;
		}

		public void setParentId(Long parentId) {
			this.parentId = parentId;
		}

		public String getCreateUser() {
			return createUser;
		}

		public void setCreateUser(String createUser) {
			this.createUser = createUser;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public String getUpdateUser() {
			return updateUser;
		}

		public void setUpdateUser(String updateUser) {
			this.updateUser = updateUser;
		}

		public Date getUpdateTime() {
			return updateTime;
		}

		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}

		public List<YunMenusShow> getChildren() {
			return children;
		}

		public void setChildren(List<YunMenusShow> children) {
			this.children = children;
		}

		@Override
		public String toString() {
			return "YunMenusShow [mId=" + mId + ", menuName=" + menuName
					+ ", menuIcon=" + menuIcon + ", menuUrl=" + menuUrl
					+ ", menuAuthorization=" + menuAuthorization + ", isOn="
					+ isOn + ", parentId=" + parentId + ", createUser="
					+ createUser + ", createTime=" + createTime
					+ ", updateUser=" + updateUser + ", updateTime="
					+ updateTime + ", children=" + children + "]";
		}
		
		
	    
	    
}
