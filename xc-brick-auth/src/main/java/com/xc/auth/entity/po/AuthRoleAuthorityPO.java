package com.xc.auth.entity.po;

import java.io.Serializable;

import lombok.*;
import lombok.Builder;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 角色权限关系表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("auth_role_authority")
public class AuthRoleAuthorityPO implements Serializable {

    private static final long serialVersionUID = 671917756378255165L;
    /**
     * 权限id
     */
    private Long authorityId;
    /**
     * 权限类型 menu:菜单 resource:资源
     */
    private String authorityType;
    /**
     * 角色id
     */
    private Long roleId;
}