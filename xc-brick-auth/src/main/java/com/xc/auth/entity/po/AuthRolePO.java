package com.xc.auth.entity.po;

import java.io.Serializable;

import com.xc.core.base.BasePO;
import lombok.*;
import lombok.Builder;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 角色表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("auth_role")
public class AuthRolePO extends BasePO implements Serializable {

    private static final long serialVersionUID = 591466457423218893L;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色编码
     */
    private String code;
    /**
     * 角色描述
     */
    private String describe;
    /**
     * 是否启用状态
     */
    private String status;
    /**
     * 是否内置角色
     */
    private String readonly;
}