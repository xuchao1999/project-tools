package com.xc.auth.repository;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xc.auth.entity.po.AuthUserPO;
import com.xc.auth.entity.dto.req.AuthUserQueryDTO;
import com.xc.auth.entity.dto.req.AuthUserSaveDTO;
import com.xc.auth.entity.po.AuthUserPO;
import com.xc.auth.entity.vo.AuthUserVO;
import com.xc.auth.mapper.IAuthUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.plugins.Page;


/**
* 用户表
*
* @author xc
* @date 2022-09-06 17:08:54
*/
@RequiredArgsConstructor
@Component
public class AuthUserRepository extends ServiceImpl<IAuthUserMapper, AuthUserPO> {

    private final IAuthUserMapper authUserMapper;

    public Page<AuthUserVO> pageQuery(AuthUserQueryDTO queryDTO) {
        Page page = new Page();
        page.setCurrent(queryDTO.getPageNum());
        page.setSize(queryDTO.getPageSize());
        Page<AuthUserVO> dataList = authUserMapper.pageQuery(page, queryDTO);
        return dataList;
    }

    public static AuthUserPO saveModelToPo(AuthUserSaveDTO saveDTO) {
        AuthUserPO po = new AuthUserPO();
        BeanUtil.copyProperties(saveDTO, po);
        return po;
    }

    public static List<AuthUserPO> saveModelsToPos(List<AuthUserSaveDTO> saveModelList) {
        if (CollUtil.isNotEmpty(saveModelList)) {
            List<AuthUserPO> dataList = new ArrayList<>();
            for (AuthUserSaveDTO saveDTO : saveModelList) {
                dataList.add(saveModelToPo(saveDTO));
            }
            return dataList;
        }
        return null;
    }

    public static AuthUserVO poToVo(AuthUserPO po) {
        AuthUserVO vo = new AuthUserVO();
        BeanUtil.copyProperties(po, vo);
        return vo;
    }

    public static List<AuthUserVO> posToVos(List<AuthUserPO> dataList) {
        if (CollUtil.isNotEmpty(dataList)) {
            List<AuthUserVO> dtoList = new ArrayList<>();
            for (AuthUserPO po : dataList) {
                dtoList.add(poToVo(po));
            }
            return dtoList;
        }
        return null;
    }
}