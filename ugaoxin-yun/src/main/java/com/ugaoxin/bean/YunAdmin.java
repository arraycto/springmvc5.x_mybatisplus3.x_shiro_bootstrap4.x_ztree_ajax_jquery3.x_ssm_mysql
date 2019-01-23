package com.ugaoxin.bean;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ugaoxin.enums.SexEnum;
@TableName("yun_admin")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class YunAdmin implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    @TableId("id")
	private Long id;

    private String adminName;

    private String adminPassword;

    private String salt;

    private String realName;

    private String email;
    
    @JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
    private SexEnum sex;   // 0代表女，1表示男
   
    private Date birthday;

    private String telephone;

    private Long roleId;

    private String lastLoginIp;

    private Date lastLoginTime;

    private String remark;

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
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword == null ? null : adminPassword.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex == null ? null : sex;
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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	@Override
	public String toString() {
		return "YunAdmin [id=" + id + ", adminName=" + adminName
				+ ", adminPassword=" + adminPassword + ", salt=" + salt
				+ ", realName=" + realName + ", email=" + email + ", sex="
				+ sex + ", birthday=" + birthday + ", telephone=" + telephone
				+ ", roleId=" + roleId + ", lastLoginIp=" + lastLoginIp
				+ ", lastLoginTime=" + lastLoginTime + ", remark=" + remark
				+ "]";
	}
    
}