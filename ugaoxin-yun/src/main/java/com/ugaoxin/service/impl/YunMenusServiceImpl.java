package com.ugaoxin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ugaoxin.bean.YunAdmin;
import com.ugaoxin.bean.YunMenus;
import com.ugaoxin.dao.YunMenusMapper;
import com.ugaoxin.service.IYunMenusService;
import com.ugaoxin.vo.RoleMenuVO;
import com.ugaoxin.vo.YunMenusShow;
@Service
public class YunMenusServiceImpl extends ServiceImpl<YunMenusMapper, YunMenus> implements IYunMenusService{
    /**
     * 左侧菜单的业务实现
     */
	@Autowired
	private YunMenusMapper yunMenusMapper;
	
	
	@Override
	public List<YunMenusShow> getLeftMenusByAdmin(YunAdmin yunAdmin) {
		 
		//1.获取登录用户的权限
		Long roleId = yunAdmin.getRoleId();
		
		//2.查询出所有菜单
		List<YunMenus> mAllList = yunMenusMapper.selectList(null);
		List<YunMenusShow> pastList = new ArrayList<YunMenusShow>();
		//3.找出相关的树形结构关系
		if(mAllList!=null&&mAllList.size()>0) {
			//3.1根据权限id查询出菜单
			  List<YunMenus> menus = yunMenusMapper.getMenusListByRoleId(roleId);
	          for(int i =0;i<menus.size();i++){
	        	  // 3.2 找出所有的根节点
	        	  if(menus.get(i).getParentId()==0){
	        		  YunMenusShow  mshow = new YunMenusShow();
       		       //3.3存放根节点信息
	        		  mshow.setMenuName(menus.get(i).getMenuName());
	        		  mshow.setMenuIcon(menus.get(i).getMenuIcon());
	        		  mshow.setMenuUrl(menus.get(i).getMenuUrl());
	        		  mshow.setIsOn(menus.get(i).getIsOn());
	        		  //3.4封装子节点的信息
	        		  List<YunMenusShow>  cList = new ArrayList<YunMenusShow>();
	        		  for (int j = 0; j < menus.size(); j++) {
	        			  
	        			  if(menus.get(j).getParentId().equals(menus.get(i).getmId())){
	        				  YunMenusShow cshow = new YunMenusShow();
	        				  cshow.setMenuName(menus.get(j).getMenuName());
	        				  cshow.setMenuIcon(menus.get(j).getMenuIcon());
	        				  cshow.setMenuUrl(menus.get(j).getMenuUrl());
	        				  cshow.setIsOn(menus.get(j).getIsOn());
	        				  // 3.5把所有符合条件的插入到子节点的集合中去
	        				  cList.add(cshow);
	        			  }
	        		  }
	        		  mshow.setChildren(cList);
	        		  pastList.add(mshow);
	        	  }
	        	  
	        	  
	          }
		}
		
		return pastList;
	}


	@Override
	public List<RoleMenuVO> getMenusByrId(Long rId) {
		
		
		        // 查询所有目录根据状态，全面角色菜单名称
				List<YunMenus> menus = yunMenusMapper.selectList(null);

				// 查找该角色拥有的权限 自己所有权限的菜单名称
				List<YunMenus> listByRoleId = yunMenusMapper.getListByRoleId(rId);

				List<RoleMenuVO> roleMenuVOs = new ArrayList<>();

				List<RoleMenuVO> newM = new ArrayList<>();
				
				// 遍历目录，设置该角色是否选中该目录
				for (YunMenus menu : menus) {
					RoleMenuVO roleMenuVO = new RoleMenuVO();
					 
						BeanUtils.copyProperties(menu, roleMenuVO);
						for (YunMenus roleMenu : listByRoleId) {
							if (roleMenuVO.getmId().equals(roleMenu.getmId())){
								roleMenuVO.setChecked(true);
								
							}
						}
						roleMenuVOs.add(roleMenuVO);
						
					 

				}
			 
				return roleMenuVOs;
	 
	}

  

}
