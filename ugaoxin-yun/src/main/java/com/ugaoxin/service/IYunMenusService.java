package com.ugaoxin.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.ugaoxin.bean.YunAdmin;
import com.ugaoxin.bean.YunMenus;
import com.ugaoxin.vo.RoleMenuVO;
import com.ugaoxin.vo.YunMenusShow;

public interface IYunMenusService extends IService<YunMenus>{ 
 
	List<YunMenusShow> getLeftMenusByAdmin(YunAdmin yunAdmin);

	List<RoleMenuVO> getMenusByrId(Long rId); 
 

}
