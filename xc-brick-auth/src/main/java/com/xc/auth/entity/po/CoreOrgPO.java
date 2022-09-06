package com.xc.auth.entity.po;

import java.io.Serializable;

import com.xc.core.base.BasePO;
import lombok.*;
import lombok.Builder;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 组织表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("core_org")
public class CoreOrgPO extends BasePO implements Serializable {

    private static final long serialVersionUID = 737154116584374256L;
    /**
     * 组织名称
     */
    private String name;
    /**
     * 简称
     */
    private String abbreviation;
    /**
     * 父id
     */
    private Long parentId;
    /**
     * 树结构
     */
    private String treePath;
    /**
     * 排序
     */
    private Integer sortValue;
    /**
     * 状态
     */
    private String status;
    /**
     * 描述
     */
    private String describe;
}