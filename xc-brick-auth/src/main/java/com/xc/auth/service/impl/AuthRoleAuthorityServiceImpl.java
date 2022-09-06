package com.xc.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xc.auth.repository.AuthRoleAuthorityRepository;
import com.xc.auth.entity.dto.req.AuthRoleAuthorityQueryDTO;
import com.xc.auth.entity.dto.req.AuthRoleAuthoritySaveDTO;
import com.xc.auth.entity.po.AuthRoleAuthorityPO;
import com.xc.auth.entity.vo.AuthRoleAuthorityVO;
import com.xc.auth.mapper.IAuthRoleAuthorityMapper;
import com.xc.auth.service.IAuthRoleAuthorityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.Lists;
import java.util.List;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 角色权限关系表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AuthRoleAuthorityServiceImpl implements IAuthRoleAuthorityService {


    private final AuthRoleAuthorityRepository authRoleAuthorityRepository;
    private final IAuthRoleAuthorityMapper authRoleAuthorityMapper;

    @Override
    public void save(AuthRoleAuthoritySaveDTO saveDTO) {
        authRoleAuthorityRepository.insertOrUpdate(AuthRoleAuthorityRepository.saveModelToPo(saveDTO));
    }

    @Override
    public void updateById(AuthRoleAuthoritySaveDTO saveDTO) {
        authRoleAuthorityRepository.updateById(AuthRoleAuthorityRepository.saveModelToPo(saveDTO));
    }

    @Override
    public void delFlagById(Long id) {
        authRoleAuthorityRepository.deleteById(id);
    }

    @Override
    public void delFlagBatch(List<Long> idList) {
        if (CollUtil.isNotEmpty(idList)) {
            authRoleAuthorityRepository.deleteBatchIds(idList);
        }
    }

    @Override
    public Page<AuthRoleAuthorityVO> pageQuery(AuthRoleAuthorityQueryDTO queryDTO) {
        Page<AuthRoleAuthorityVO> data = authRoleAuthorityRepository.pageQuery(queryDTO);
        return data;
    }


    @Override
    public AuthRoleAuthorityVO getOneById(Long id) {
        AuthRoleAuthorityPO po = authRoleAuthorityRepository.selectById(id);
        if(po == null){
            return null;
        }
        AuthRoleAuthorityVO data = AuthRoleAuthorityRepository.poToVo(po);
        return data;
    }
}