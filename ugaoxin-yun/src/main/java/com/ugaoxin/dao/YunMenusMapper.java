package com.ugaoxin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ugaoxin.bean.YunMenus;

public interface YunMenusMapper  extends BaseMapper<YunMenus>{
    int deleteByPrimaryKey(Long mId);

    Integer insert(YunMenus record);

    int insertSelective(YunMenus record);

    YunMenus selectByPrimaryKey(Long mId);

    int updateByPrimaryKeySelective(YunMenus record);

    int updateByPrimaryKey(YunMenus record);
    
    // 搭建shiro权限控制框架时候使用
    // 1.通过xml配置，也就是自动化生成的代码去使用查询
    // 2.直接用@select注解的方式，推荐使用。
    @Select("SELECT * from yun_menus ym   LEFT JOIN yun_roles_menus yrm ON yrm.m_id=ym.m_id and yrm.r_id=#{0}")
	List<YunMenus> getMenusListByRoleId(Long rId); 
    @Select("SELECT * from yun_menus ym  , yun_roles_menus yrm  where yrm.m_id=ym.m_id and yrm.r_id=#{0}")
    List<YunMenus>   getListByRoleId(Long rId);
}