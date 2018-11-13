package com.sjth.erpcloud.module.auth.service;

import com.sjth.erpcloud.module.auth.dao.AuthUserDao;
import com.sjth.erpcloud.module.auth.entity.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthUserService{
    @Autowired
    private AuthUserDao userDao;
    
    public AuthUser findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}