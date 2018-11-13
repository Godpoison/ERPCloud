package com.sjth.erpcloud.module.auth.dao;

import com.sjth.erpcloud.module.auth.entity.AuthUser;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface AuthUserDao {
	AuthUser findByUsername(String username);
}