package ${classInfo.packageNameForApi}.dto;

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
    <#if fieldItem.fieldName == "delFlag"
    || fieldItem.fieldName == "createBy"
    || fieldItem.fieldName == "updateBy"
    || fieldItem.fieldName == "createTime"
    || fieldItem.fieldName == "updateTime">
        <#assign commonColumnCount = commonColumnCount + 1 >
    </#if>
</#list>

import java.io.Serializable;
import lombok.Data;

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
@Data
public class ${classInfo.className}Dto implements Serializable {
private static final long serialVersionUID = ${serialVersionUID}L;
<#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
    <#list classInfo.fieldList as fieldItem >
        <#if fieldItem.fieldName != "companyId"
        && fieldItem.fieldName != "delFlag"
        && fieldItem.fieldName != "createBy"
        && fieldItem.fieldName != "createDeptId"
        && fieldItem.fieldName != "updateBy"
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