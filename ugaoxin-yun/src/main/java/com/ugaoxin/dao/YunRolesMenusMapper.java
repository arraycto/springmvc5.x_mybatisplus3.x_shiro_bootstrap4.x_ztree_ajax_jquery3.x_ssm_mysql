package com.ugaoxin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ugaoxin.bean.YunRolesMenusKey;

public interface YunRolesMenusMapper extends BaseMapper<YunRolesMenusKey> {
    int deleteByPrimaryKey(YunRolesMenusKey key);

    Integer insert(YunRolesMenusKey record);

    int insertSelective(YunRolesMenusKey record);
}