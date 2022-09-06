package com.xc.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xc.auth.repository.AuthRoleOrgRepository;
import com.xc.auth.entity.dto.req.AuthRoleOrgQueryDTO;
import com.xc.auth.entity.dto.req.AuthRoleOrgSaveDTO;
import com.xc.auth.entity.po.AuthRoleOrgPO;
import com.xc.auth.entity.vo.AuthRoleOrgVO;
import com.xc.auth.mapper.IAuthRoleOrgMapper;
import com.xc.auth.service.IAuthRoleOrgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.Lists;
import java.util.List;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 角色组织关系表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AuthRoleOrgServiceImpl implements IAuthRoleOrgService {


    private final AuthRoleOrgRepository authRoleOrgRepository;
    private final IAuthRoleOrgMapper authRoleOrgMapper;

    @Override
    public void save(AuthRoleOrgSaveDTO saveDTO) {
        authRoleOrgRepository.insertOrUpdate(AuthRoleOrgRepository.saveModelToPo(saveDTO));
    }

    @Override
    public void updateById(AuthRoleOrgSaveDTO saveDTO) {
        authRoleOrgRepository.updateById(AuthRoleOrgRepository.saveModelToPo(saveDTO));
    }

    @Override
    public void delFlagById(Long id) {
        authRoleOrgRepository.deleteById(id);
    }

    @Override
    public void delFlagBatch(List<Long> idList) {
        if (CollUtil.isNotEmpty(idList)) {
            authRoleOrgRepository.deleteBatchIds(idList);
        }
    }

    @Override
    public Page<AuthRoleOrgVO> pageQuery(AuthRoleOrgQueryDTO queryDTO) {
        Page<AuthRoleOrgVO> data = authRoleOrgRepository.pageQuery(queryDTO);
        return data;
    }


    @Override
    public AuthRoleOrgVO getOneById(Long id) {
        AuthRoleOrgPO po = authRoleOrgRepository.selectById(id);
        if(po == null){
            return null;
        }
        AuthRoleOrgVO data = AuthRoleOrgRepository.poToVo(po);
        return data;
    }
}