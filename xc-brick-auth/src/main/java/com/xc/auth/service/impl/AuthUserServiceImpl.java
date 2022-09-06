package com.xc.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xc.auth.repository.AuthUserRepository;
import com.xc.auth.entity.dto.req.AuthUserQueryDTO;
import com.xc.auth.entity.dto.req.AuthUserSaveDTO;
import com.xc.auth.entity.po.AuthUserPO;
import com.xc.auth.entity.vo.AuthUserVO;
import com.xc.auth.mapper.IAuthUserMapper;
import com.xc.auth.service.IAuthUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.Lists;
import java.util.List;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 用户表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AuthUserServiceImpl implements IAuthUserService {


    private final AuthUserRepository authUserRepository;
    private final IAuthUserMapper authUserMapper;

    @Override
    public void save(AuthUserSaveDTO saveDTO) {
        authUserRepository.insertOrUpdate(AuthUserRepository.saveModelToPo(saveDTO));
    }

    @Override
    public void updateById(AuthUserSaveDTO saveDTO) {
        authUserRepository.updateById(AuthUserRepository.saveModelToPo(saveDTO));
    }

    @Override
    public void delFlagById(Long id) {
        authUserRepository.deleteById(id);
    }

    @Override
    public void delFlagBatch(List<Long> idList) {
        if (CollUtil.isNotEmpty(idList)) {
            authUserRepository.deleteBatchIds(idList);
        }
    }

    @Override
    public Page<AuthUserVO> pageQuery(AuthUserQueryDTO queryDTO) {
        Page<AuthUserVO> data = authUserRepository.pageQuery(queryDTO);
        return data;
    }


    @Override
    public AuthUserVO getOneById(Long id) {
        AuthUserPO po = authUserRepository.selectById(id);
        if(po == null){
            return null;
        }
        AuthUserVO data = AuthUserRepository.poToVo(po);
        return data;
    }
}