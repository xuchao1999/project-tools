package com.xc.auth.entity.po;

import java.io.Serializable;

import com.xc.core.base.BasePO;
import lombok.*;
import lombok.Builder;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 菜单表
 *
 * @author xc
 * @date 2022-09-01 11:13:24
 */
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("auth_menu")
public class AuthMenuPO extends BasePO implements Serializable {

    private static final long serialVersionUID = 184658471993612656L;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 功能描述
     */
    private String describe;
    /**
     * 是否是公开菜单
     */
    private String isPublic;
    /**
     * 对应路由path
     */
    private String path;
    /**
     * 对应路由组件component
     */
    private String component;
    /**
     * 是否启用
     */
    private String isEnable;
    /**
     * 排序
     */
    private Integer sortValue;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 菜单分组
     */
    private String group;
    /**
     * 父级菜单id
     */
    private Long parentId;
}