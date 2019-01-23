package com.ugaoxin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ugaoxin.bean.YunRoles;
import com.ugaoxin.bean.YunUsers;

public interface YunUsersMapper extends BaseMapper<YunUsers>{
    int deleteByPrimaryKey(Long userId);

    Integer insert(YunUsers record);

    int insertSelective(YunUsers record);

    YunUsers selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(YunUsers record);

    int updateByPrimaryKey(YunUsers record);
}