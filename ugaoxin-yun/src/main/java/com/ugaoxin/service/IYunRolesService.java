package com.ugaoxin.service;

import com.baomidou.mybatisplus.service.IService;
import com.ugaoxin.bean.YunRoles;

/**  
 * Copyright © Array老师. All rights reserved.
 * @Title: IYunRolesService.java
 * @Prject: ugaoxin-yun
 * @Package: com.ugaoxin.service
 * @Description: TODO
 * @author: Array老师 
 * @version: V1.0  
 */
public interface IYunRolesService extends IService<YunRoles>{

	Integer doAddRole(YunRoles yunRoles, String[] menuIds);

	Integer doUpdateRole(YunRoles yunRoles, String[] menuIds);  

}
