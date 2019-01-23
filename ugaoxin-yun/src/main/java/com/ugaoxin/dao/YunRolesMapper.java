package com.ugaoxin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ugaoxin.bean.YunRoles;

public interface YunRolesMapper extends BaseMapper<YunRoles>{
    int deleteByPrimaryKey(Long rId);

    Integer insert(YunRoles record);

    int insertSelective(YunRoles record);

    YunRoles selectByPrimaryKey(Long rId);

    int updateByPrimaryKeySelective(YunRoles record);

    int updateByPrimaryKey(YunRoles record);
}