package com.xc.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xc.auth.repository.CoreOrgRepository;
import com.xc.auth.entity.dto.req.CoreOrgQueryDTO;
import com.xc.auth.entity.dto.req.CoreOrgSaveDTO;
import com.xc.auth.entity.po.CoreOrgPO;
import com.xc.auth.entity.vo.CoreOrgVO;
import com.xc.auth.mapper.ICoreOrgMapper;
import com.xc.auth.service.ICoreOrgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.Lists;
import java.util.List;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 组织表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CoreOrgServiceImpl implements ICoreOrgService {


    private final CoreOrgRepository coreOrgRepository;
    private final ICoreOrgMapper coreOrgMapper;

    @Override
    public void save(CoreOrgSaveDTO saveDTO) {
        coreOrgRepository.insertOrUpdate(CoreOrgRepository.saveModelToPo(saveDTO));
    }

    @Override
    public void updateById(CoreOrgSaveDTO saveDTO) {
        coreOrgRepository.updateById(CoreOrgRepository.saveModelToPo(saveDTO));
    }

    @Override
    public void delFlagById(Long id) {
        coreOrgRepository.deleteById(id);
    }

    @Override
    public void delFlagBatch(List<Long> idList) {
        if (CollUtil.isNotEmpty(idList)) {
            coreOrgRepository.deleteBatchIds(idList);
        }
    }

    @Override
    public Page<CoreOrgVO> pageQuery(CoreOrgQueryDTO queryDTO) {
        Page<CoreOrgVO> data = coreOrgRepository.pageQuery(queryDTO);
        return data;
    }


    @Override
    public CoreOrgVO getOneById(Long id) {
        CoreOrgPO po = coreOrgRepository.selectById(id);
        if(po == null){
            return null;
        }
        CoreOrgVO data = CoreOrgRepository.poToVo(po);
        return data;
    }
}