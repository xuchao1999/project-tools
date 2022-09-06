package com.xc.auth.repository;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xc.auth.entity.po.AuthUserRolePO;
import com.xc.auth.entity.dto.req.AuthUserRoleQueryDTO;
import com.xc.auth.entity.dto.req.AuthUserRoleSaveDTO;
import com.xc.auth.entity.po.AuthUserRolePO;
import com.xc.auth.entity.vo.AuthUserRoleVO;
import com.xc.auth.mapper.IAuthUserRoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.plugins.Page;


/**
* 用户角色关系
*
* @author xc
* @date 2022-09-06 17:08:54
*/
@RequiredArgsConstructor
@Component
public class AuthUserRoleRepository extends ServiceImpl<IAuthUserRoleMapper, AuthUserRolePO> {

    private final IAuthUserRoleMapper authUserRoleMapper;

    public Page<AuthUserRoleVO> pageQuery(AuthUserRoleQueryDTO queryDTO) {
        Page page = new Page();
        page.setCurrent(queryDTO.getPageNum());
        page.setSize(queryDTO.getPageSize());
        Page<AuthUserRoleVO> dataList = authUserRoleMapper.pageQuery(page, queryDTO);
        return dataList;
    }

    public static AuthUserRolePO saveModelToPo(AuthUserRoleSaveDTO saveDTO) {
        AuthUserRolePO po = new AuthUserRolePO();
        BeanUtil.copyProperties(saveDTO, po);
        return po;
    }

    public static List<AuthUserRolePO> saveModelsToPos(List<AuthUserRoleSaveDTO> saveModelList) {
        if (CollUtil.isNotEmpty(saveModelList)) {
            List<AuthUserRolePO> dataList = new ArrayList<>();
            for (AuthUserRoleSaveDTO saveDTO : saveModelList) {
                dataList.add(saveModelToPo(saveDTO));
            }
            return dataList;
        }
        return null;
    }

    public static AuthUserRoleVO poToVo(AuthUserRolePO po) {
        AuthUserRoleVO vo = new AuthUserRoleVO();
        BeanUtil.copyProperties(po, vo);
        return vo;
    }

    public static List<AuthUserRoleVO> posToVos(List<AuthUserRolePO> dataList) {
        if (CollUtil.isNotEmpty(dataList)) {
            List<AuthUserRoleVO> dtoList = new ArrayList<>();
            for (AuthUserRolePO po : dataList) {
                dtoList.add(poToVo(po));
            }
            return dtoList;
        }
        return null;
    }
}