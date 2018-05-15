package com.infohold.smallpay.map.dao;

import com.infohold.smallpay.map.model.UserCheck;

public interface UserCheckMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserCheck record);

    int insertSelective(UserCheck record);

    UserCheck selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserCheck record);

    int updateByPrimaryKey(UserCheck record);
}