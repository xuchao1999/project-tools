<#if classInfo.moduleName?length gt 0>
package ${classInfo.packageName}.${classInfo.moduleName}.mapper;
<#else>
package ${classInfo.packageName}.mapper;
</#if>

<#if classInfo.moduleName?length gt 0>
import ${classInfo.packageName}.${classInfo.moduleName}.entity.dto.req.${classInfo.className}QueryDTO;
import ${classInfo.packageName}.${classInfo.moduleName}.entity.po.${classInfo.className}PO;
import ${classInfo.packageName}.${classInfo.moduleName}.entity.vo.${classInfo.className}VO;
<#else>
import ${classInfo.packageName}.entity.dto.req.${classInfo.className}QueryDTO;
import ${classInfo.packageName}.entity.po.${classInfo.className}PO;
import ${classInfo.packageName}.entity.vo.${classInfo.className}VO;
</#if>
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * ${classInfo.classComment}
 *
 * @author ${authorName}
 * @date ${.now?string('yyyy-MM-dd HH:mm:ss')}
 */
@Mapper
public interface I${classInfo.className}Mapper extends BaseMapper<${classInfo.className}PO> {

    /**
     * 分页查询
     *
     * @author ${authorName}
     * @date ${.now?string('yyyy/MM/dd')}
     **/
    Page<${classInfo.className}VO> pageQuery(Page page, ${classInfo.className}QueryDTO queryDTO);

    /**
     * 逻辑删除
     *
     * @param id
     * @param userId
     * @return
     */
    int logicDelete(@Param("id") Long id, @Param("updateBy") Long userId);

    /**
     * 批量逻辑删除
     *
     * @param idList
     * @param userId
     * @return
     */
    int logicDeleteBatch(@Param("idList") List<Long> idList, @Param("updateBy") Long userId);
}