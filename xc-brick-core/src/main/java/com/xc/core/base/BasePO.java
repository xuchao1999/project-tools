package com.xc.core.base;

import cn.hutool.core.date.DateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 描述: po公共部分
 *
 * @author xc
 * @date 20220831 17:25:06
 * @since v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasePO {
    /**
     * id
     */
    private String id;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 更新人
     */
    private String updateUser;
    /**
     * 创建时间
     */
    private DateTime createTime;
    /**
     * 更新时间
     */
    private DateTime updateTime;
}
