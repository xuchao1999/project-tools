package com.xc.auth.repository;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xc.auth.entity.po.AuthRolePO;
import com.xc.auth.entity.dto.req.AuthRoleQueryDTO;
import com.xc.auth.entity.dto.req.AuthRoleSaveDTO;
import com.xc.auth.entity.po.AuthRolePO;
import com.xc.auth.entity.vo.AuthRoleVO;
import com.xc.auth.mapper.IAuthRoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.plugins.Page;


/**
* 角色表
*
* @author xc
* @date 2022-09-06 17:08:54
*/
@RequiredArgsConstructor
@Component
public class AuthRoleRepository extends ServiceImpl<IAuthRoleMapper, AuthRolePO> {

    private final IAuthRoleMapper authRoleMapper;

    public Page<AuthRoleVO> pageQuery(AuthRoleQueryDTO queryDTO) {
        Page page = new Page();
        page.setCurrent(queryDTO.getPageNum());
        page.setSize(queryDTO.getPageSize());
        Page<AuthRoleVO> dataList = authRoleMapper.pageQuery(page, queryDTO);
        return dataList;
    }

    public static AuthRolePO saveModelToPo(AuthRoleSaveDTO saveDTO) {
        AuthRolePO po = new AuthRolePO();
        BeanUtil.copyProperties(saveDTO, po);
        return po;
    }

    public static List<AuthRolePO> saveModelsToPos(List<AuthRoleSaveDTO> saveModelList) {
        if (CollUtil.isNotEmpty(saveModelList)) {
            List<AuthRolePO> dataList = new ArrayList<>();
            for (AuthRoleSaveDTO saveDTO : saveModelList) {
                dataList.add(saveModelToPo(saveDTO));
            }
            return dataList;
        }
        return null;
    }

    public static AuthRoleVO poToVo(AuthRolePO po) {
        AuthRoleVO vo = new AuthRoleVO();
        BeanUtil.copyProperties(po, vo);
        return vo;
    }

    public static List<AuthRoleVO> posToVos(List<AuthRolePO> dataList) {
        if (CollUtil.isNotEmpty(dataList)) {
            List<AuthRoleVO> dtoList = new ArrayList<>();
            for (AuthRolePO po : dataList) {
                dtoList.add(poToVo(po));
            }
            return dtoList;
        }
        return null;
    }
}