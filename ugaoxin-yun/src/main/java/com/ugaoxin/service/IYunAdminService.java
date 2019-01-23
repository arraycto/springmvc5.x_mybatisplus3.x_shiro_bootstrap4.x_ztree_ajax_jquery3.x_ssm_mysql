package com.ugaoxin.service;

import com.baomidou.mybatisplus.service.IService;
import com.ugaoxin.bean.YunAdmin;
import com.ugaoxin.bean.YunRoles;

/**  
 * Copyright © Array老师. All rights reserved.
 * @Title: IYunAdminService.java
 * @Prject: ugaoxin-yun
 * @Package: com.ugaoxin.service
 * @Description: TODO
 * @author: Array老师 
 * @version: V1.0  
 */
public interface IYunAdminService extends IService<YunAdmin>{ 
 
	Integer doAdmin(YunAdmin yunAdmin);

    Integer doUpdateAdmin(YunAdmin yunAdmin);

}
