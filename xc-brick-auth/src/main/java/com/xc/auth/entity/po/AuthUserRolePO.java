package com.xc.auth.entity.po;

import java.io.Serializable;

import lombok.*;
import lombok.Builder;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 用户角色关系
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("auth_user_role")
public class AuthUserRolePO implements Serializable {

    private static final long serialVersionUID = 958368645113273954L;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 用户id
     */
    private Long userId;
}