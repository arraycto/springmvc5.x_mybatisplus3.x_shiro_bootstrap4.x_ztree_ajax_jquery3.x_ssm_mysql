package com.ugaoxin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ugaoxin.bean.YunHistory;

public interface YunHistoryMapper extends BaseMapper<YunHistory> {
    int deleteByPrimaryKey(Long id);

    Integer insert(YunHistory record);

    int insertSelective(YunHistory record);

    YunHistory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(YunHistory record);

    int updateByPrimaryKey(YunHistory record);
}