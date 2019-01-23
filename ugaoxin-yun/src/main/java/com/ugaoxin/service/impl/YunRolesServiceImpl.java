package com.ugaoxin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ugaoxin.bean.YunRoles;
import com.ugaoxin.bean.YunRolesMenusKey;
import com.ugaoxin.dao.YunRolesMapper;
import com.ugaoxin.dao.YunRolesMenusMapper;
import com.ugaoxin.service.IYunRolesService;
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
public class YunRolesServiceImpl extends ServiceImpl<YunRolesMapper, YunRoles> implements IYunRolesService{
	
         @Resource
	     private YunRolesMapper yunRolesMapper;
         @Resource
         private YunRolesMenusMapper yunRolesMenusMapper;
	    /*
	    * 处理ztree和角色信息
	    * @see com.ugaoxin.service.IYunMenusService#doAddRole(com.ugaoxin.bean.YunRoles, java.lang.String[])
	    */
		 
		public Integer doAddRole(YunRoles yunRoles, String[] menuIds) {
			int count =0;
			 // 1.确定角色的Id
			 // 目前最好的ID生成策略 ，雪花算法   snow   twitter  Instagram 都使用 
			 yunRoles.setrId(IDGenerator.getLongId());
			 yunRoles.setUpdateTime(new Date());
			 // 插入到YunRoles对应的表中
			 yunRolesMapper.insert(yunRoles);
			 
			 
			 // 插入ztree
			 if(menuIds!=null&&menuIds.length>0){
				 
				 //List<YunRolesMenusKey> yun = new ArrayList<YunRolesMenusKey>();
				 for(int i=0;i<menuIds.length;i++){
					 YunRolesMenusKey yunKey = new YunRolesMenusKey();
					 yunKey.setmId(Long.valueOf(menuIds[i]));
					 yunKey.setrId(yunRoles.getrId());
					 yunRolesMenusMapper.insert(yunKey);
					 count++;
				 }
				 
			 }
			   
			return count;
		}

		/*
		 * 角色更新操作
		 * @see com.ugaoxin.service.IYunRolesService#doUpdateRole(com.ugaoxin.bean.YunRoles, java.lang.String[])
		 */
		public Integer doUpdateRole(YunRoles yunRoles, String[] menuIds) {
			int count =0;
			 // 1.确定角色的Id
			 // 目前最好的ID生成策略 ，雪花算法   snow   twitter  Instagram 都使用  
			 yunRoles.setUpdateTime(new Date());
			 // 插入到YunRoles对应的表中
			 yunRolesMapper.updateById(yunRoles);
			 
			 
			 // 插入ztree
			 if(menuIds!=null&&menuIds.length>0){
				 
				 //List<YunRolesMenusKey> yun = new ArrayList<YunRolesMenusKey>();
				 for(int i=0;i<menuIds.length;i++){
					 YunRolesMenusKey yunKey = new YunRolesMenusKey();
					 yunKey.setmId(Long.valueOf(menuIds[i]));
					 yunKey.setrId(yunRoles.getrId());
					 //为了数据的一致性，先删除，后插入  =更新
					 yunRolesMenusMapper.deleteById(yunRoles.getrId());
					 yunRolesMenusMapper.insert(yunKey);
					 count++;
				 }
				 
			 }
			   
			return count;
		}

		
	 
}
