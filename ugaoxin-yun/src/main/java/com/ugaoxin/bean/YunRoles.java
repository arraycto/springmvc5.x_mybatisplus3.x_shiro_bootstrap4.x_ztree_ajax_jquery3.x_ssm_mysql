package com.ugaoxin.bean;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
@TableName("yun_roles")
public class YunRoles {
	@TableId("r_id")
    private Long rId;

    private String roleName;

    private String roleDescription;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private Integer isSys;

    private Integer isOn;

    public Long getrId() {
        return rId;
    }

    public void setrId(Long rId) {
        this.rId = rId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription == null ? null : roleDescription.trim();
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

    public Integer getIsSys() {
        return isSys;
    }

    public void setIsSys(Integer isSys) {
        this.isSys = isSys;
    }

    public Integer getIsOn() {
        return isOn;
    }

    public void setIsOn(Integer isOn) {
        this.isOn = isOn;
    }

	@Override
	public String toString() {
		return "YunRoles [rId=" + rId + ", roleName=" + roleName
				+ ", roleDescription=" + roleDescription + ", createUser="
				+ createUser + ", createTime=" + createTime + ", updateUser="
				+ updateUser + ", updateTime=" + updateTime + ", isSys="
				+ isSys + ", isOn=" + isOn + "]";
	}
    
}