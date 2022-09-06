package com.xc.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xc.auth.repository.CommonLoginLogRepository;
import com.xc.auth.entity.dto.req.CommonLoginLogQueryDTO;
import com.xc.auth.entity.dto.req.CommonLoginLogSaveDTO;
import com.xc.auth.entity.po.CommonLoginLogPO;
import com.xc.auth.entity.vo.CommonLoginLogVO;
import com.xc.auth.mapper.ICommonLoginLogMapper;
import com.xc.auth.service.ICommonLoginLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.Lists;
import java.util.List;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 字典名
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CommonLoginLogServiceImpl implements ICommonLoginLogService {


    private final CommonLoginLogRepository commonLoginLogRepository;
    private final ICommonLoginLogMapper commonLoginLogMapper;

    @Override
    public void save(CommonLoginLogSaveDTO saveDTO) {
        commonLoginLogRepository.insertOrUpdate(CommonLoginLogRepository.saveModelToPo(saveDTO));
    }

    @Override
    public void updateById(CommonLoginLogSaveDTO saveDTO) {
        commonLoginLogRepository.updateById(CommonLoginLogRepository.saveModelToPo(saveDTO));
    }

    @Override
    public void delFlagById(Long id) {
        commonLoginLogRepository.deleteById(id);
    }

    @Override
    public void delFlagBatch(List<Long> idList) {
        if (CollUtil.isNotEmpty(idList)) {
            commonLoginLogRepository.deleteBatchIds(idList);
        }
    }

    @Override
    public Page<CommonLoginLogVO> pageQuery(CommonLoginLogQueryDTO queryDTO) {
        Page<CommonLoginLogVO> data = commonLoginLogRepository.pageQuery(queryDTO);
        return data;
    }


    @Override
    public CommonLoginLogVO getOneById(Long id) {
        CommonLoginLogPO po = commonLoginLogRepository.selectById(id);
        if(po == null){
            return null;
        }
        CommonLoginLogVO data = CommonLoginLogRepository.poToVo(po);
        return data;
    }
}