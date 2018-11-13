package com.sjth.erpcloud.module.auth.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
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