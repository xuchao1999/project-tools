package com.xc.auth.repository;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xc.auth.entity.po.AuthRoleOrgPO;
import com.xc.auth.entity.dto.req.AuthRoleOrgQueryDTO;
import com.xc.auth.entity.dto.req.AuthRoleOrgSaveDTO;
import com.xc.auth.entity.po.AuthRoleOrgPO;
import com.xc.auth.entity.vo.AuthRoleOrgVO;
import com.xc.auth.mapper.IAuthRoleOrgMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.plugins.Page;


/**
* 角色组织关系表
*
* @author xc
* @date 2022-09-06 17:08:54
*/
@RequiredArgsConstructor
@Component
public class AuthRoleOrgRepository extends ServiceImpl<IAuthRoleOrgMapper, AuthRoleOrgPO> {

    private final IAuthRoleOrgMapper authRoleOrgMapper;

    public Page<AuthRoleOrgVO> pageQuery(AuthRoleOrgQueryDTO queryDTO) {
        Page page = new Page();
        page.setCurrent(queryDTO.getPageNum());
        page.setSize(queryDTO.getPageSize());
        Page<AuthRoleOrgVO> dataList = authRoleOrgMapper.pageQuery(page, queryDTO);
        return dataList;
    }

    public static AuthRoleOrgPO saveModelToPo(AuthRoleOrgSaveDTO saveDTO) {
        AuthRoleOrgPO po = new AuthRoleOrgPO();
        BeanUtil.copyProperties(saveDTO, po);
        return po;
    }

    public static List<AuthRoleOrgPO> saveModelsToPos(List<AuthRoleOrgSaveDTO> saveModelList) {
        if (CollUtil.isNotEmpty(saveModelList)) {
            List<AuthRoleOrgPO> dataList = new ArrayList<>();
            for (AuthRoleOrgSaveDTO saveDTO : saveModelList) {
                dataList.add(saveModelToPo(saveDTO));
            }
            return dataList;
        }
        return null;
    }

    public static AuthRoleOrgVO poToVo(AuthRoleOrgPO po) {
        AuthRoleOrgVO vo = new AuthRoleOrgVO();
        BeanUtil.copyProperties(po, vo);
        return vo;
    }

    public static List<AuthRoleOrgVO> posToVos(List<AuthRoleOrgPO> dataList) {
        if (CollUtil.isNotEmpty(dataList)) {
            List<AuthRoleOrgVO> dtoList = new ArrayList<>();
            for (AuthRoleOrgPO po : dataList) {
                dtoList.add(poToVo(po));
            }
            return dtoList;
        }
        return null;
    }
}