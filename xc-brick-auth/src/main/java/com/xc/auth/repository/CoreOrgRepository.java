package com.xc.auth.repository;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xc.auth.entity.po.CoreOrgPO;
import com.xc.auth.entity.dto.req.CoreOrgQueryDTO;
import com.xc.auth.entity.dto.req.CoreOrgSaveDTO;
import com.xc.auth.entity.po.CoreOrgPO;
import com.xc.auth.entity.vo.CoreOrgVO;
import com.xc.auth.mapper.ICoreOrgMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.plugins.Page;


/**
* 组织表
*
* @author xc
* @date 2022-09-06 17:08:54
*/
@RequiredArgsConstructor
@Component
public class CoreOrgRepository extends ServiceImpl<ICoreOrgMapper, CoreOrgPO> {

    private final ICoreOrgMapper coreOrgMapper;

    public Page<CoreOrgVO> pageQuery(CoreOrgQueryDTO queryDTO) {
        Page page = new Page();
        page.setCurrent(queryDTO.getPageNum());
        page.setSize(queryDTO.getPageSize());
        Page<CoreOrgVO> dataList = coreOrgMapper.pageQuery(page, queryDTO);
        return dataList;
    }

    public static CoreOrgPO saveModelToPo(CoreOrgSaveDTO saveDTO) {
        CoreOrgPO po = new CoreOrgPO();
        BeanUtil.copyProperties(saveDTO, po);
        return po;
    }

    public static List<CoreOrgPO> saveModelsToPos(List<CoreOrgSaveDTO> saveModelList) {
        if (CollUtil.isNotEmpty(saveModelList)) {
            List<CoreOrgPO> dataList = new ArrayList<>();
            for (CoreOrgSaveDTO saveDTO : saveModelList) {
                dataList.add(saveModelToPo(saveDTO));
            }
            return dataList;
        }
        return null;
    }

    public static CoreOrgVO poToVo(CoreOrgPO po) {
        CoreOrgVO vo = new CoreOrgVO();
        BeanUtil.copyProperties(po, vo);
        return vo;
    }

    public static List<CoreOrgVO> posToVos(List<CoreOrgPO> dataList) {
        if (CollUtil.isNotEmpty(dataList)) {
            List<CoreOrgVO> dtoList = new ArrayList<>();
            for (CoreOrgPO po : dataList) {
                dtoList.add(poToVo(po));
            }
            return dtoList;
        }
        return null;
    }
}