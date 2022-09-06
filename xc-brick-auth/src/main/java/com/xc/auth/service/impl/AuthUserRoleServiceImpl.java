package com.xc.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xc.auth.repository.AuthUserRoleRepository;
import com.xc.auth.entity.dto.req.AuthUserRoleQueryDTO;
import com.xc.auth.entity.dto.req.AuthUserRoleSaveDTO;
import com.xc.auth.entity.po.AuthUserRolePO;
import com.xc.auth.entity.vo.AuthUserRoleVO;
import com.xc.auth.mapper.IAuthUserRoleMapper;
import com.xc.auth.service.IAuthUserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.Lists;
import java.util.List;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 用户角色关系
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AuthUserRoleServiceImpl implements IAuthUserRoleService {


    private final AuthUserRoleRepository authUserRoleRepository;
    private final IAuthUserRoleMapper authUserRoleMapper;

    @Override
    public void save(AuthUserRoleSaveDTO saveDTO) {
        authUserRoleRepository.insertOrUpdate(AuthUserRoleRepository.saveModelToPo(saveDTO));
    }

    @Override
    public void updateById(AuthUserRoleSaveDTO saveDTO) {
        authUserRoleRepository.updateById(AuthUserRoleRepository.saveModelToPo(saveDTO));
    }

    @Override
    public void delFlagById(Long id) {
        authUserRoleRepository.deleteById(id);
    }

    @Override
    public void delFlagBatch(List<Long> idList) {
        if (CollUtil.isNotEmpty(idList)) {
            authUserRoleRepository.deleteBatchIds(idList);
        }
    }

    @Override
    public Page<AuthUserRoleVO> pageQuery(AuthUserRoleQueryDTO queryDTO) {
        Page<AuthUserRoleVO> data = authUserRoleRepository.pageQuery(queryDTO);
        return data;
    }


    @Override
    public AuthUserRoleVO getOneById(Long id) {
        AuthUserRolePO po = authUserRoleRepository.selectById(id);
        if(po == null){
            return null;
        }
        AuthUserRoleVO data = AuthUserRoleRepository.poToVo(po);
        return data;
    }
}