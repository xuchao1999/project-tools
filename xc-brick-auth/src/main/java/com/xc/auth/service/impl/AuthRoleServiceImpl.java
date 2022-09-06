package com.xc.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xc.auth.repository.AuthRoleRepository;
import com.xc.auth.entity.dto.req.AuthRoleQueryDTO;
import com.xc.auth.entity.dto.req.AuthRoleSaveDTO;
import com.xc.auth.entity.po.AuthRolePO;
import com.xc.auth.entity.vo.AuthRoleVO;
import com.xc.auth.mapper.IAuthRoleMapper;
import com.xc.auth.service.IAuthRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.Lists;
import java.util.List;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 角色表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AuthRoleServiceImpl implements IAuthRoleService {


    private final AuthRoleRepository authRoleRepository;
    private final IAuthRoleMapper authRoleMapper;

    @Override
    public void save(AuthRoleSaveDTO saveDTO) {
        authRoleRepository.insertOrUpdate(AuthRoleRepository.saveModelToPo(saveDTO));
    }

    @Override
    public void updateById(AuthRoleSaveDTO saveDTO) {
        authRoleRepository.updateById(AuthRoleRepository.saveModelToPo(saveDTO));
    }

    @Override
    public void delFlagById(Long id) {
        authRoleRepository.deleteById(id);
    }

    @Override
    public void delFlagBatch(List<Long> idList) {
        if (CollUtil.isNotEmpty(idList)) {
            authRoleRepository.deleteBatchIds(idList);
        }
    }

    @Override
    public Page<AuthRoleVO> pageQuery(AuthRoleQueryDTO queryDTO) {
        Page<AuthRoleVO> data = authRoleRepository.pageQuery(queryDTO);
        return data;
    }


    @Override
    public AuthRoleVO getOneById(Long id) {
        AuthRolePO po = authRoleRepository.selectById(id);
        if(po == null){
            return null;
        }
        AuthRoleVO data = AuthRoleRepository.poToVo(po);
        return data;
    }
}