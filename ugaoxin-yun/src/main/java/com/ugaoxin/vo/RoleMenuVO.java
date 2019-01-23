package com.ugaoxin.vo;

import java.util.List;

import com.ugaoxin.bean.YunMenus;
import com.ugaoxin.bean.YunRoles;
import com.ugaoxin.bean.YunRolesMenusKey;

public class RoleMenuVO extends YunMenus {

	private static final long serialVersionUID = 1L;
	 
	 
	
	/** 是否选中 */
	private boolean checked;

	 

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	 
}
