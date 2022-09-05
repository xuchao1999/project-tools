<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<#if classInfo.moduleName?length gt 0>
<mapper namespace="${packageName}.${classInfo.moduleName}.mapper.I${classInfo.className}Mapper">
    <#else>
    <mapper namespace="${packageName}.mapper.I${classInfo.className}Mapper">
        </#if>

        <#if classInfo.moduleName?length gt 0>
        <resultMap id="BaseResultMap" type="${classInfo.packageName}.entity.po.${classInfo.className}PO">
            <#else>
            <resultMap id="BaseResultMap" type="${classInfo.packageName}.entity.po.${classInfo.className}PO">
                </#if>
                <#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
                    <#list classInfo.fieldList as fieldItem >
                        <result column="${fieldItem.columnName}" property="${fieldItem.fieldName}"/>
                    </#list>
                </#if>
            </resultMap>

            <sql id="Base_Column_List">
                <#if classInfo.fieldList?exists && classInfo.fieldList?size gt 0>
                    <#list classInfo.fieldList as fieldItem >
                        <#if fieldItem.columnName == "name">`</#if>${fieldItem.columnName}<#if fieldItem.columnName == "name">`</#if><#if fieldItem_has_next>,</#if>
                    </#list>
                </#if>
            </sql>

            <select id="pageQuery" resultType="${classInfo.packageName}.entity.vo.${classInfo.className}VO">
                SELECT <include refid="Base_Column_List"/> FROM ${classInfo.tableName}
                <where>
                    del_flag = 0
                </where>
                ORDER BY id DESC
            </select>

            <update id="logicDelete" parameterType="map">
                UPDATE ${classInfo.tableName}
                SET
                del_flag = 1,
                update_by = ${r"#{updateBy}"},
                update_time = REPLACE(UNIX_TIMESTAMP(CURRENT_TIMESTAMP(3)), '.', '')
                WHERE id = ${r"#{id}"} AND del_flag = 0
            </update>

            <update id="logicDeleteBatch">
                UPDATE ${classInfo.tableName}
                SET
                del_flag = 1,
                update_by = ${r"#{updateBy}"},
                update_time = REPLACE(UNIX_TIMESTAMP(CURRENT_TIMESTAMP(3)), '.', '')
                WHERE
                id IN
                <foreach collection="idList" index="index" item="id" open="(" separator="," close=")">
                    ${r"#{id}"}
                </foreach>
                AND del_flag = 0
            </update>
    </mapper>