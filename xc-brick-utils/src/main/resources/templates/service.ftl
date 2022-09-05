<#if classInfo.moduleName?length gt 0>
package ${classInfo.packageName}.${classInfo.moduleName}.service;
<#else>
package ${classInfo.packageName}.service;
</#if>

<#if classInfo.moduleName?length gt 0>
import ${classInfo.packageName}.${classInfo.moduleName}.entity.dto.req.${classInfo.className}QueryDTO;
import ${classInfo.packageName}.${classInfo.moduleName}.entity.dto.req.${classInfo.className}SaveDTO;
import ${classInfo.packageName}.${classInfo.moduleName}.entity.vo.${classInfo.className}VO;
<#else>
import ${classInfo.packageName}.entity.dto.req.${classInfo.className}QueryDTO;
import ${classInfo.packageName}.entity.dto.req.${classInfo.className}SaveDTO;
import ${classInfo.packageName}.entity.vo.${classInfo.className}VO;
</#if>
import com.baomidou.mybatisplus.plugins.Page;
import java.util.List;

/**
 * ${classInfo.classComment}
 *
 * @author ${authorName}
 * @date ${.now?string('yyyy-MM-dd HH:mm:ss')}
 */
public interface I${classInfo.className}Service {

    /**
     * 保存（添加或修改）
     *
     * @param saveDTO
     * @return
     * @author ${authorName}
     */
    void save(${classInfo.className}SaveDTO saveDTO);

    /**
     * 修改
     *
     * @param saveDTO
     * @return
     * @author ${authorName}
     */
    void updateById(${classInfo.className}SaveDTO saveDTO);

    /**
     * 根据ID逻辑删除记录
     *
     * @param id
     * @return
     * @author ${authorName}
     */
    void delFlagById(Long id);

    /**
     * 根据ID逻辑删除记录（批量)
     *
     * @param idList
     * @return
     * @author ${authorName}
     */
    void delFlagBatch(List<Long> idList);

    /**
     * 分页查询
     *
     * @param queryDTO
     * @return
     * @author ${authorName}
     */
    Page<${classInfo.className}VO> pageQuery(${classInfo.className}QueryDTO queryDTO);

    /**
    * 根据ID查询记录
    *
    * @param id
    * @return
    * @author ${authorName}
    */
    ${classInfo.className}VO getOneById(Long id);
}