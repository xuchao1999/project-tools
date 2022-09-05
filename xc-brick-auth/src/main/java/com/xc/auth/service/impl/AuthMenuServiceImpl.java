package com.xc.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xc.auth.repository.AuthMenuRepository;
import com.xc.auth.entity.dto.req.AuthMenuQueryDTO;
import com.xc.auth.entity.dto.req.AuthMenuSaveDTO;
import com.xc.auth.entity.po.AuthMenuPO;
import com.xc.auth.entity.vo.AuthMenuVO;
import com.xc.auth.mapper.IAuthMenuMapper;
import com.xc.auth.service.IAuthMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.Lists;
import java.util.List;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 菜单表
 *
 * @author xc
 * @date 2022-09-01 11:13:24
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AuthMenuServiceImpl implements IAuthMenuService {


    private final AuthMenuRepository authMenuRepository;
    private final IAuthMenuMapper authMenuMapper;

    @Override
    public void save(AuthMenuSaveDTO saveDTO) {
        authMenuRepository.insertOrUpdate(AuthMenuRepository.saveModelToPo(saveDTO));
    }

    @Override
    public void updateById(AuthMenuSaveDTO saveDTO) {
        authMenuRepository.updateById(AuthMenuRepository.saveModelToPo(saveDTO));
    }

    @Override
    public void delFlagById(Long id) {
        authMenuRepository.deleteById(id);
    }

    @Override
    public void delFlagBatch(List<Long> idList) {
        if (CollUtil.isNotEmpty(idList)) {
            authMenuRepository.deleteBatchIds(idList);
        }
    }

    @Override
    public Page<AuthMenuVO> pageQuery(AuthMenuQueryDTO queryDTO) {
        Page<AuthMenuVO> data = authMenuRepository.pageQuery(queryDTO);
        return data;
    }


    @Override
    public AuthMenuVO getOneById(Long id) {
        AuthMenuPO po = authMenuRepository.selectById(id);
        if(po == null){
            return null;
        }
        AuthMenuVO data = AuthMenuRepository.poToVo(po);
        return data;
    }
}