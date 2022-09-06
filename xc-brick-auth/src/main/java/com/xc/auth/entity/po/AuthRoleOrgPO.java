package com.xc.auth.entity.po;

import java.io.Serializable;

import lombok.*;
import lombok.Builder;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 角色组织关系表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("auth_role_org")
public class AuthRoleOrgPO implements Serializable {

    private static final long serialVersionUID = 779398929931333153L;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 组织id
     */
    private Long orgId;
}