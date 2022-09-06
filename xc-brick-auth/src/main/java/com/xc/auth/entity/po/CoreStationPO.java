package com.xc.auth.entity.po;

import java.io.Serializable;

import com.xc.core.base.BasePO;
import lombok.*;
import lombok.Builder;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 岗位表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("core_station")
public class CoreStationPO extends BasePO implements Serializable {

    private static final long serialVersionUID = 427463831626356521L;
    /**
     * 岗位名称
     */
    private String name;
    /**
     * 组织id
     */
    private Long orgId;
    /**
     * 是否启用状态
     */
    private String status;
    /**
     * 描述
     */
    private String describe;
}