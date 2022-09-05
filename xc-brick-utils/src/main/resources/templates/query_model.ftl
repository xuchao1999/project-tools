<#if classInfo.moduleName?length gt 0>
package ${classInfo.packageName}.${classInfo.moduleName}.entity.dto.req;
<#else>
package ${classInfo.packageName}.entity.dto.req;
</#if>
<#-- 引入数据类型 -->
<#assign listTypeCount = 0 />
<#assign bigDecimalTypeCount = 0 />
<#assign dateTypeCount = 0 />
<#list classInfo.fieldList as fieldItem >
<#-- 引入数据类型 -->
    <#if fieldItem.fieldClass == "List">
        <#assign listTypeCount = listTypeCount + 1 >
    </#if>
    <#if fieldItem.fieldClass == "BigDecimal">
        <#assign bigDecimalTypeCount = bigDecimalTypeCount + 1 >
    </#if>
    <#if fieldItem.fieldClass == "Date">
        <#assign dateTypeCount = dateTypeCount + 1 >
    </#if>
</#list>

import java.io.Serializable;
import lombok.*;
import lombok.experimental.SuperBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.xc.core.base.BasePage;

<#if listTypeCount gt 0>
    import java.util.List;
</#if>
<#if bigDecimalTypeCount gt 0>
    import java.math.BigDecimal;
</#if>
<#if dateTypeCount gt 0>
import java.util.Date;
</#if>

/**
* ${classInfo.classComment}
*
* @author ${authorName}
* @date ${.now?string('yyyy-MM-dd HH:mm:ss')}
*/
@ApiModel(value = "${classInfo.className?uncap_first}", description = "${classInfo.classComment}")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ${classInfo.className}QueryDTO extends BasePage implements Serializable {

    private static final long serialVersionUID = ${serialVersionUID}L;
<#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
<#list classInfo.fieldList as fieldItem >
    @ApiModelProperty(value = "${fieldItem.fieldComment}")
    private ${fieldItem.fieldClass} ${fieldItem.fieldName?uncap_first};
</#list>
</#if>
}
