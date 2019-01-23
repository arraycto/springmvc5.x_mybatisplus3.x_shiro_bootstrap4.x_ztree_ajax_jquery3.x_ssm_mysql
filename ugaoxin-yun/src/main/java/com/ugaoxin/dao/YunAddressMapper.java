package com.ugaoxin.dao;

import com.ugaoxin.bean.YunAddress;

public interface YunAddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(YunAddress record);

    int insertSelective(YunAddress record);

    YunAddress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(YunAddress record);

    int updateByPrimaryKey(YunAddress record);
}