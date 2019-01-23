package com.ugaoxin.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ugaoxin.bean.YunAdmin;
import com.ugaoxin.bean.YunRoles;
import com.ugaoxin.bean.YunRolesMenusKey;
import com.ugaoxin.dao.YunAdminMapper;
import com.ugaoxin.dao.YunRolesMapper;
import com.ugaoxin.dao.YunRolesMenusMapper;
import com.ugaoxin.service.IYunAdminService;
import com.ugaoxin.util.IDGenerator;

/**  
 * Copyright © Array老师. All rights reserved.
 * @Title: YunAdminServiceImpl.java
 * @Prject: ugaoxin-yun
 * @Package: com.ugaoxin.service.impl
 * @Description: TODO
 * @author: Array老师 
 * @version: V1.0  
 */
@Service
public class YunAdminServiceImpl extends ServiceImpl<YunAdminMapper, YunAdmin> implements IYunAdminService{
	
         @Resource
	     private YunRolesMapper yunRolesMapper;
         @Resource
         private YunRolesMenusMapper yunRolesMenusMapper;
         @Resource
	     private YunAdminMapper yunAdminMapper;
		 
		public Integer doAdmin(YunAdmin yunAdmin) {
			int count =0;
			 // 1.确定角色的Id
			 // 目前最好的ID生成策略 ，雪花算法   snow   twitter  Instagram 都使用 
			// yunRoles.setrId(IDGenerator.getLongId());
			//yunAdmin.setUpdateTime(new Date());
			 // 插入到YunRoles对应的表中
			count = yunAdminMapper.insert(yunAdmin);
			 
			   
			return count;
		}

		/*
		 * 管理员的更新操作
		 * @see com.ugaoxin.service.IYunRolesService#doUpdateRole(com.ugaoxin.bean.YunRoles, java.lang.String[])
		 */
		public Integer doUpdateAdmin(YunAdmin yunAdmin) {
			int count =0;
			 // 1.确定角色的Id
			 // 目前最好的ID生成策略 ，雪花算法   snow   twitter  Instagram 都使用  
			 
			 // 插入到yunAdminMapper对应的表中
			count = yunAdminMapper.updateById(yunAdmin);
			  
			   
			return count;
		}

		
	 
}
