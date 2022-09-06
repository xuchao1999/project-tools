package com.xc.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xc.auth.repository.CommonOptLogRepository;
import com.xc.auth.entity.dto.req.CommonOptLogQueryDTO;
import com.xc.auth.entity.dto.req.CommonOptLogSaveDTO;
import com.xc.auth.entity.po.CommonOptLogPO;
import com.xc.auth.entity.vo.CommonOptLogVO;
import com.xc.auth.mapper.ICommonOptLogMapper;
import com.xc.auth.service.ICommonOptLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.Lists;
import java.util.List;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 操作日志表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CommonOptLogServiceImpl implements ICommonOptLogService {


    private final CommonOptLogRepository commonOptLogRepository;
    private final ICommonOptLogMapper commonOptLogMapper;

    @Override
    public void save(CommonOptLogSaveDTO saveDTO) {
        commonOptLogRepository.insertOrUpdate(CommonOptLogRepository.saveModelToPo(saveDTO));
    }

    @Override
    public void updateById(CommonOptLogSaveDTO saveDTO) {
        commonOptLogRepository.updateById(CommonOptLogRepository.saveModelToPo(saveDTO));
    }

    @Override
    public void delFlagById(Long id) {
        commonOptLogRepository.deleteById(id);
    }

    @Override
    public void delFlagBatch(List<Long> idList) {
        if (CollUtil.isNotEmpty(idList)) {
            commonOptLogRepository.deleteBatchIds(idList);
        }
    }

    @Override
    public Page<CommonOptLogVO> pageQuery(CommonOptLogQueryDTO queryDTO) {
        Page<CommonOptLogVO> data = commonOptLogRepository.pageQuery(queryDTO);
        return data;
    }


    @Override
    public CommonOptLogVO getOneById(Long id) {
        CommonOptLogPO po = commonOptLogRepository.selectById(id);
        if(po == null){
            return null;
        }
        CommonOptLogVO data = CommonOptLogRepository.poToVo(po);
        return data;
    }
}