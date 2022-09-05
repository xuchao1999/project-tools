package com.xc.core.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 描述： 分页基础实体
 *
 * @author xc
 * @date 20220901 11:10:18
 * @since v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BasePage {
    /**
     * The Page num.
     */
    @ApiModelProperty(
            value = "页码",
            required = true
    )
    private Integer pageNum = 1;
    /**
     * The Page size.
     */
    @ApiModelProperty(
            value = "页大小",
            required = true
    )
    private Integer pageSize = 10;
}
