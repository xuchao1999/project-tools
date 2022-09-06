package com.xc.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xc.auth.repository.CoreStationRepository;
import com.xc.auth.entity.dto.req.CoreStationQueryDTO;
import com.xc.auth.entity.dto.req.CoreStationSaveDTO;
import com.xc.auth.entity.po.CoreStationPO;
import com.xc.auth.entity.vo.CoreStationVO;
import com.xc.auth.mapper.ICoreStationMapper;
import com.xc.auth.service.ICoreStationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.Lists;
import java.util.List;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 岗位表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CoreStationServiceImpl implements ICoreStationService {


    private final CoreStationRepository coreStationRepository;
    private final ICoreStationMapper coreStationMapper;

    @Override
    public void save(CoreStationSaveDTO saveDTO) {
        coreStationRepository.insertOrUpdate(CoreStationRepository.saveModelToPo(saveDTO));
    }

    @Override
    public void updateById(CoreStationSaveDTO saveDTO) {
        coreStationRepository.updateById(CoreStationRepository.saveModelToPo(saveDTO));
    }

    @Override
    public void delFlagById(Long id) {
        coreStationRepository.deleteById(id);
    }

    @Override
    public void delFlagBatch(List<Long> idList) {
        if (CollUtil.isNotEmpty(idList)) {
            coreStationRepository.deleteBatchIds(idList);
        }
    }

    @Override
    public Page<CoreStationVO> pageQuery(CoreStationQueryDTO queryDTO) {
        Page<CoreStationVO> data = coreStationRepository.pageQuery(queryDTO);
        return data;
    }


    @Override
    public CoreStationVO getOneById(Long id) {
        CoreStationPO po = coreStationRepository.selectById(id);
        if(po == null){
            return null;
        }
        CoreStationVO data = CoreStationRepository.poToVo(po);
        return data;
    }
}