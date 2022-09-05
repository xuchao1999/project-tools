<#if classInfo.moduleName?length gt 0>
package ${classInfo.packageName}.${classInfo.moduleName}.service.impl;
<#else>
package ${classInfo.packageName}.service.impl;
</#if>

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
<#if classInfo.moduleName?length gt 0>
import ${classInfo.packageName}.${classInfo.moduleName}.repository.${classInfo.className}Repository;
import ${classInfo.packageName}.${classInfo.moduleName}.entity.dto.req.${classInfo.className}QueryDTO;
import ${classInfo.packageName}.${classInfo.moduleName}.entity.dto.req.${classInfo.className}SaveDTO;
import ${classInfo.packageName}.${classInfo.moduleName}.entity.po.${classInfo.className}PO;
import ${classInfo.packageName}.${classInfo.moduleName}.entity.vo.${classInfo.className}VO;
import ${classInfo.packageName}.${classInfo.moduleName}.mapper.I${classInfo.className}Mapper;
import ${classInfo.packageName}.${classInfo.moduleName}.service.I${classInfo.className}Service;
<#else>
import ${classInfo.packageName}.repository.${classInfo.className}Repository;
import ${classInfo.packageName}.entity.dto.req.${classInfo.className}QueryDTO;
import ${classInfo.packageName}.entity.dto.req.${classInfo.className}SaveDTO;
import ${classInfo.packageName}.entity.po.${classInfo.className}PO;
import ${classInfo.packageName}.entity.vo.${classInfo.className}VO;
import ${classInfo.packageName}.mapper.I${classInfo.className}Mapper;
import ${classInfo.packageName}.service.I${classInfo.className}Service;
</#if>
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.Lists;
import java.util.List;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * ${classInfo.classComment}
 *
 * @author ${authorName}
 * @date ${.now?string('yyyy-MM-dd HH:mm:ss')}
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ${classInfo.className}ServiceImpl implements I${classInfo.className}Service {


    private final ${classInfo.className}Repository ${classInfo.className?uncap_first}Repository;
    private final I${classInfo.className}Mapper ${classInfo.className?uncap_first}Mapper;

    @Override
    public void save(${classInfo.className}SaveDTO saveDTO) {
        ${classInfo.className?uncap_first}Repository.insertOrUpdate(${classInfo.className}Repository.saveModelToPo(saveDTO));
    }

    @Override
    public void updateById(${classInfo.className}SaveDTO saveDTO) {
        ${classInfo.className?uncap_first}Repository.updateById(${classInfo.className}Repository.saveModelToPo(saveDTO));
    }

    @Override
    public void delFlagById(Long id) {
        ${classInfo.className?uncap_first}Repository.deleteById(id);
    }

    @Override
    public void delFlagBatch(List<Long> idList) {
        if (CollUtil.isNotEmpty(idList)) {
            ${classInfo.className?uncap_first}Repository.deleteBatchIds(idList);
        }
    }

    @Override
    public Page<${classInfo.className}VO> pageQuery(${classInfo.className}QueryDTO queryDTO) {
        Page<${classInfo.className}VO> data = ${classInfo.className?uncap_first}Repository.pageQuery(queryDTO);
        return data;
    }


    @Override
    public ${classInfo.className}VO getOneById(Long id) {
        ${classInfo.className}PO po = ${classInfo.className?uncap_first}Repository.selectById(id);
        if(po == null){
            return null;
        }
        ${classInfo.className}VO data = ${classInfo.className}Repository.poToVo(po);
        return data;
    }
}