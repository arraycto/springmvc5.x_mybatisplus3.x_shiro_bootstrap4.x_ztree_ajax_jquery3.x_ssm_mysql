package com.ugaoxin.service;

import com.baomidou.mybatisplus.service.IService;
import com.ugaoxin.bean.YunMenus;
import com.ugaoxin.bean.YunRoles;

public interface IYunNavMenusService extends IService<YunMenus>{

	Integer doAdd(YunMenus yunMenus, Long menusId);

	Integer doUpdate(YunMenus yunMenus, Long menusId);  

}
