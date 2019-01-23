package com.ugaoxin.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ugaoxin.bean.YunMenus;
import com.ugaoxin.bean.YunRoles;
import com.ugaoxin.bean.YunRolesMenusKey;
import com.ugaoxin.dao.YunMenusMapper;
import com.ugaoxin.dao.YunRolesMapper;
import com.ugaoxin.dao.YunRolesMenusMapper;
import com.ugaoxin.service.IYunNavMenusService;
import com.ugaoxin.util.IDGenerator;

/**  
 * Copyright © Array老师. All rights reserved.
 * @Title: YunRolesServiceImpl.java
 * @Prject: ugaoxin-yun
 * @Package: com.ugaoxin.service.impl
 * @Description: TODO
 * @author: Array老师 
 * @version: V1.0  
 */
@Service
public class YunNavMenusServiceImpl extends ServiceImpl<YunMenusMapper, YunMenus> implements IYunNavMenusService{
	
         @Resource
	     private YunMenusMapper yunMenusMapper; 
	  
	 

		@Override
		public Integer doAdd(YunMenus yunMenus, Long menusId) {
			 
			//确定parentId
			yunMenus.setParentId(menusId);
			//目前为了演示暂时写为0，Array老师提醒：根据自己的业务灵活应对
			yunMenus.setIsOn("0"); 
			Integer count = yunMenusMapper.insert(yunMenus);
			
			return count;
		}



		@Override
		public Integer doUpdate(YunMenus yunMenus, Long menusId) {
			 
			 Integer count = yunMenusMapper.updateById(yunMenus);
			
			return count;
		}

		
	 
}
