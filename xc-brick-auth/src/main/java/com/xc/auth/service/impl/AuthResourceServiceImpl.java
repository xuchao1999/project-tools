package com.xc.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xc.auth.repository.AuthResourceRepository;
import com.xc.auth.entity.dto.req.AuthResourceQueryDTO;
import com.xc.auth.entity.dto.req.AuthResourceSaveDTO;
import com.xc.auth.entity.po.AuthResourcePO;
import com.xc.auth.entity.vo.AuthResourceVO;
import com.xc.auth.mapper.IAuthResourceMapper;
import com.xc.auth.service.IAuthResourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.Lists;
import java.util.List;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 资源表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AuthResourceServiceImpl implements IAuthResourceService {


    private final AuthResourceRepository authResourceRepository;
    private final IAuthResourceMapper authResourceMapper;

    @Override
    public void save(AuthResourceSaveDTO saveDTO) {
        authResourceRepository.insertOrUpdate(AuthResourceRepository.saveModelToPo(saveDTO));
    }

    @Override
    public void updateById(AuthResourceSaveDTO saveDTO) {
        authResourceRepository.updateById(AuthResourceRepository.saveModelToPo(saveDTO));
    }

    @Override
    public void delFlagById(Long id) {
        authResourceRepository.deleteById(id);
    }

    @Override
    public void delFlagBatch(List<Long> idList) {
        if (CollUtil.isNotEmpty(idList)) {
            authResourceRepository.deleteBatchIds(idList);
        }
    }

    @Override
    public Page<AuthResourceVO> pageQuery(AuthResourceQueryDTO queryDTO) {
        Page<AuthResourceVO> data = authResourceRepository.pageQuery(queryDTO);
        return data;
    }


    @Override
    public AuthResourceVO getOneById(Long id) {
        AuthResourcePO po = authResourceRepository.selectById(id);
        if(po == null){
            return null;
        }
        AuthResourceVO data = AuthResourceRepository.poToVo(po);
        return data;
    }
}