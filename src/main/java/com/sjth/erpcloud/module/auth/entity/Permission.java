package com.sjth.erpcloud.module.auth.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@TableName("t_permission")
public class Permission {
	@TableId("permission_id")
    private  Integer permissionId;
	@TableField("permission_name")
    private  String  permissionName;
}