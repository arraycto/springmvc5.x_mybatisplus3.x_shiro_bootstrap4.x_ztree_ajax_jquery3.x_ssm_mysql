package com.ugaoxin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ugaoxin.bean.YunAdmin;

public interface YunAdminMapper  extends BaseMapper<YunAdmin>{
    int deleteByPrimaryKey(Long id);

    Integer insert(YunAdmin record);

    int insertSelective(YunAdmin record);

    YunAdmin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(YunAdmin record);

    int updateByPrimaryKey(YunAdmin record);
}