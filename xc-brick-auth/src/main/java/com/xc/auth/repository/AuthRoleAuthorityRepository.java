package com.xc.auth.repository;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xc.auth.entity.po.AuthRoleAuthorityPO;
import com.xc.auth.entity.dto.req.AuthRoleAuthorityQueryDTO;
import com.xc.auth.entity.dto.req.AuthRoleAuthoritySaveDTO;
import com.xc.auth.entity.po.AuthRoleAuthorityPO;
import com.xc.auth.entity.vo.AuthRoleAuthorityVO;
import com.xc.auth.mapper.IAuthRoleAuthorityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.plugins.Page;


/**
* 角色权限关系表
*
* @author xc
* @date 2022-09-06 17:08:54
*/
@RequiredArgsConstructor
@Component
public class AuthRoleAuthorityRepository extends ServiceImpl<IAuthRoleAuthorityMapper, AuthRoleAuthorityPO> {

    private final IAuthRoleAuthorityMapper authRoleAuthorityMapper;

    public Page<AuthRoleAuthorityVO> pageQuery(AuthRoleAuthorityQueryDTO queryDTO) {
        Page page = new Page();
        page.setCurrent(queryDTO.getPageNum());
        page.setSize(queryDTO.getPageSize());
        Page<AuthRoleAuthorityVO> dataList = authRoleAuthorityMapper.pageQuery(page, queryDTO);
        return dataList;
    }

    public static AuthRoleAuthorityPO saveModelToPo(AuthRoleAuthoritySaveDTO saveDTO) {
        AuthRoleAuthorityPO po = new AuthRoleAuthorityPO();
        BeanUtil.copyProperties(saveDTO, po);
        return po;
    }

    public static List<AuthRoleAuthorityPO> saveModelsToPos(List<AuthRoleAuthoritySaveDTO> saveModelList) {
        if (CollUtil.isNotEmpty(saveModelList)) {
            List<AuthRoleAuthorityPO> dataList = new ArrayList<>();
            for (AuthRoleAuthoritySaveDTO saveDTO : saveModelList) {
                dataList.add(saveModelToPo(saveDTO));
            }
            return dataList;
        }
        return null;
    }

    public static AuthRoleAuthorityVO poToVo(AuthRoleAuthorityPO po) {
        AuthRoleAuthorityVO vo = new AuthRoleAuthorityVO();
        BeanUtil.copyProperties(po, vo);
        return vo;
    }

    public static List<AuthRoleAuthorityVO> posToVos(List<AuthRoleAuthorityPO> dataList) {
        if (CollUtil.isNotEmpty(dataList)) {
            List<AuthRoleAuthorityVO> dtoList = new ArrayList<>();
            for (AuthRoleAuthorityPO po : dataList) {
                dtoList.add(poToVo(po));
            }
            return dtoList;
        }
        return null;
    }
}