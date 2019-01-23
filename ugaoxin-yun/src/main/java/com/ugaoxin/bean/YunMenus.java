package com.ugaoxin.bean;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**  
 * Copyright © Array老师. All rights reserved.
 * @Title: YunMenus.java
 * @Prject: ugaoxin-yun
 * @Package: com.ugaoxin.bean
 * @Description: TODO
 * @author: Array老师 
 * @version: V1.0  
 */
@TableName("yun_menus")
public class YunMenus implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 

	@TableId("m_id")
    private Long mId;

    private String menuName;

    private String menuIcon;

    private String menuUrl;

    private String menuAuthorization;

    private String isOn;

    private Long parentId;

    private String createUser;
    @JsonSerialize
    private Date createTime;

    private String updateUser;
    @JsonSerialize
    private Date updateTime;

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
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon == null ? null : menuIcon.trim();
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    public String getMenuAuthorization() {
        return menuAuthorization;
    }

    public void setMenuAuthorization(String menuAuthorization) {
        this.menuAuthorization = menuAuthorization == null ? null : menuAuthorization.trim();
    }

    public String getIsOn() {
        return isOn;
    }

    public void setIsOn(String isOn) {
        this.isOn = isOn == null ? null : isOn.trim();
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
        this.createUser = createUser == null ? null : createUser.trim();
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
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}