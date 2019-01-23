package com.ugaoxin.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ugaoxin.bean.YunRoles;
import com.ugaoxin.bean.YunRolesMenusKey;
import com.ugaoxin.bean.YunUsers;
import com.ugaoxin.dao.YunRolesMapper;
import com.ugaoxin.dao.YunRolesMenusMapper;
import com.ugaoxin.dao.YunUsersMapper;
import com.ugaoxin.service.IYunUserService;
import com.ugaoxin.util.IDGenerator;


/**  
 * Copyright © Array老师. All rights reserved.
 * @Title: YunUserServiceImpl.java
 * @Prject: ugaoxin-yun
 * @Package: com.ugaoxin.service.impl
 * @Description: TODO
 * @author: Array老师 
 * @version: V1.0  
 */
@Service
public class YunUserServiceImpl extends ServiceImpl<YunUsersMapper, YunUsers> implements IYunUserService{
	
     /*    @Resource
	     private YunRolesMapper yunRolesMapper;
         @Resource
         private YunRolesMenusMapper yunRolesMenusMapper;
	     
		 
		 public Integer doAddUser(YunUsers yunUsers) {
			
			return null;
			 
		}*/
 
}
