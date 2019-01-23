package com.ugaoxin.bean;

import java.util.Date;

public class YunUsers {
    private Long userId;

    private Integer id;

    private String userName;

    private String realName;

    private String password;

    private String sex;

    private Date birthday;

    private String telephone;

    private String postCode;

    private String isOn;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private String createUser;

    private Date lastLoginTime;

    private String lastLoginIp;

    private Long score;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    public String getIsOn() {
        return isOn;
    }

    public void setIsOn(String isOn) {
        this.isOn = isOn == null ? null : isOn.trim();
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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

	@Override
	public String toString() {
		return "YunUsers [userId=" + userId + ", id=" + id + ", userName="
				+ userName + ", realName=" + realName + ", password="
				+ password + ", sex=" + sex + ", birthday=" + birthday
				+ ", telephone=" + telephone + ", postCode=" + postCode
				+ ", isOn=" + isOn + ", createTime=" + createTime
				+ ", updateUser=" + updateUser + ", updateTime=" + updateTime
				+ ", createUser=" + createUser + ", lastLoginTime="
				+ lastLoginTime + ", lastLoginIp=" + lastLoginIp + ", score="
				+ score + "]";
	}
    
}