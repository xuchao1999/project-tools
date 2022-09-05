<#if classInfo.moduleName?length gt 0>
package ${classInfo.packageName}.${classInfo.moduleName}.entity.po;
<#else>
package ${classInfo.packageName}.entity.po;
</#if>
<#-- 引入数据类型 -->
<#assign listTypeCount = 0 />
<#assign bigDecimalTypeCount = 0 />
<#assign dateTypeCount = 0 />
<#-- 是否继承BasePo -->
<#assign commonColumnCount = 0 />
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
<#-- 是否继承BasePo -->
    <#if fieldItem.fieldName == "id"
    || fieldItem.fieldName == "createUser"
    || fieldItem.fieldName == "updateUser"
    || fieldItem.fieldName == "createTime"
    || fieldItem.fieldName == "updateTime">
        <#assign commonColumnCount = commonColumnCount + 1 >
    </#if>
</#list>

import java.io.Serializable;

<#if commonColumnCount == 5>
import com.xc.core.base.BasePO;
</#if>
import lombok.*;
import lombok.Builder;
import com.baomidou.mybatisplus.annotation.TableName;

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
<#if commonColumnCount == 5>
@EqualsAndHashCode
</#if>
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("${classInfo.tableName}")
<#if commonColumnCount == 5>
public class ${classInfo.className}PO extends BasePO implements Serializable {
<#else>
public class ${classInfo.className}PO implements Serializable {
</#if>

    private static final long serialVersionUID = ${serialVersionUID}L;
<#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
<#list classInfo.fieldList as fieldItem >
<#if fieldItem.fieldName != "id"
&& fieldItem.fieldName != "createUser"
&& fieldItem.fieldName != "updateUser"
&& fieldItem.fieldName != "createTime"
&& fieldItem.fieldName != "updateTime">
    /**
     * ${fieldItem.fieldComment}
     */
    private ${fieldItem.fieldClass} ${fieldItem.fieldName?uncap_first};
</#if>
</#list>
</#if>
}