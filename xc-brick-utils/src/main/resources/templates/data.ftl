<#if classInfo.moduleName?length gt 0>
package ${classInfo.packageName}.${classInfo.moduleName}.repository;
<#else>
package ${classInfo.packageName}.repository;
</#if>

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
<#if classInfo.moduleName?length gt 0>
import ${classInfo.packageName}.${classInfo.moduleName}.entity.dto.req.${classInfo.className}QueryDTO;
import ${classInfo.packageName}.${classInfo.moduleName}.entity.dto.req.${classInfo.className}SaveDTO;
import ${classInfo.packageName}.${classInfo.moduleName}.entity.po.${classInfo.className}PO;
import ${classInfo.packageName}.${classInfo.moduleName}.entity.vo.${classInfo.className}VO;
import ${classInfo.packageName}.${classInfo.moduleName}.mapper.I${classInfo.className}Mapper;
<#else>
import ${classInfo.packageName}.entity.po.${classInfo.className}PO;
import ${classInfo.packageName}.entity.dto.req.${classInfo.className}QueryDTO;
import ${classInfo.packageName}.entity.dto.req.${classInfo.className}SaveDTO;
import ${classInfo.packageName}.entity.po.${classInfo.className}PO;
import ${classInfo.packageName}.entity.vo.${classInfo.className}VO;
import ${classInfo.packageName}.mapper.I${classInfo.className}Mapper;
</#if>
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.plugins.Page;


/**
* ${classInfo.classComment}
*
* @author ${authorName}
* @date ${.now?string('yyyy-MM-dd HH:mm:ss')}
*/
@RequiredArgsConstructor
@Component
public class ${classInfo.className}Repository extends ServiceImpl<I${classInfo.className}Mapper, ${classInfo.className}PO> {

    private final I${classInfo.className}Mapper ${classInfo.className?uncap_first}Mapper;

    public Page<${classInfo.className}VO> pageQuery(${classInfo.className}QueryDTO queryDTO) {
        Page page = new Page();
        page.setCurrent(queryDTO.getPageNum());
        page.setSize(queryDTO.getPageSize());
        Page<${classInfo.className}VO> dataList = ${classInfo.className?uncap_first}Mapper.pageQuery(page, queryDTO);
        return dataList;
    }

    public static ${classInfo.className}PO saveModelToPo(${classInfo.className}SaveDTO saveDTO) {
        ${classInfo.className}PO po = new ${classInfo.className}PO();
        BeanUtil.copyProperties(saveDTO, po);
        return po;
    }

    public static List<${classInfo.className}PO> saveModelsToPos(List<${classInfo.className}SaveDTO> saveModelList) {
        if (CollUtil.isNotEmpty(saveModelList)) {
            List<${classInfo.className}PO> dataList = new ArrayList<>();
            for (${classInfo.className}SaveDTO saveDTO : saveModelList) {
                dataList.add(saveModelToPo(saveDTO));
            }
            return dataList;
        }
        return null;
    }

    public static ${classInfo.className}VO poToVo(${classInfo.className}PO po) {
        ${classInfo.className}VO vo = new ${classInfo.className}VO();
        BeanUtil.copyProperties(po, vo);
        return vo;
    }

    public static List<${classInfo.className}VO> posToVos(List<${classInfo.className}PO> dataList) {
        if (CollUtil.isNotEmpty(dataList)) {
            List<${classInfo.className}VO> dtoList = new ArrayList<>();
            for (${classInfo.className}PO po : dataList) {
                dtoList.add(poToVo(po));
            }
            return dtoList;
        }
        return null;
    }
}