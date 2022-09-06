package com.xc.auth.entity.po;

import java.io.Serializable;

import com.xc.core.base.BasePO;
import lombok.*;
import lombok.Builder;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 资源表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("auth_resource")
public class AuthResourcePO extends BasePO implements Serializable {

    private static final long serialVersionUID = 514468715783299613L;
    /**
     * 资源编码
     */
    private String code;
    /**
     * 接口名称
     */
    private String name;
    /**
     * 菜单id
     */
    private Long menuId;
    /**
     * http请求方式
     */
    private String method;
    /**
     * 接口请求url
     */
    private String url;
    /**
     * 接口描述
     */
    private String describe;
}