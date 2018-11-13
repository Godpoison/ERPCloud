package com.sjth.erpcloud.module.auth.entity;

import java.util.HashSet;
import java.util.Set;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@TableName("t_user")
public class AuthUser {
	@TableId("user_id")
    private  Integer userId;
	@TableField("user_name")
    private  String userName;
	@TableField("password")
    private  String password;
    private  Set<Role> roles = new HashSet<>();
}
