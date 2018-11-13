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
@TableName("t_role")
public class Role {
	@TableId("role_id")
    private  Integer roleId;
	@TableField("role_name")
    private  String roleName;
    private  Set<Permission> permissions = new HashSet<>();
}
