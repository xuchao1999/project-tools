package com.xc.auth.repository;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xc.auth.entity.po.AuthMenuPO;
import com.xc.auth.entity.dto.req.AuthMenuQueryDTO;
import com.xc.auth.entity.dto.req.AuthMenuSaveDTO;
import com.xc.auth.entity.po.AuthMenuPO;
import com.xc.auth.entity.vo.AuthMenuVO;
import com.xc.auth.mapper.IAuthMenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.plugins.Page;


/**
* 菜单表
*
* @author xc
* @date 2022-09-01 11:13:24
*/
@RequiredArgsConstructor
@Component
public class AuthMenuRepository extends ServiceImpl<IAuthMenuMapper, AuthMenuPO> {

    private final IAuthMenuMapper authMenuMapper;

    public Page<AuthMenuVO> pageQuery(AuthMenuQueryDTO queryDTO) {
        Page page = new Page();
        page.setCurrent(queryDTO.getPageNum());
        page.setSize(queryDTO.getPageSize());
        Page<AuthMenuVO> dataList = authMenuMapper.pageQuery(page, queryDTO);
        return dataList;
    }

    public static AuthMenuPO saveModelToPo(AuthMenuSaveDTO saveDTO) {
        AuthMenuPO po = new AuthMenuPO();
        BeanUtil.copyProperties(saveDTO, po);
        return po;
    }

    public static List<AuthMenuPO> saveModelsToPos(List<AuthMenuSaveDTO> saveModelList) {
        if (CollUtil.isNotEmpty(saveModelList)) {
            List<AuthMenuPO> dataList = new ArrayList<>();
            for (AuthMenuSaveDTO saveDTO : saveModelList) {
                dataList.add(saveModelToPo(saveDTO));
            }
            return dataList;
        }
        return null;
    }

    public static AuthMenuVO poToVo(AuthMenuPO po) {
        AuthMenuVO vo = new AuthMenuVO();
        BeanUtil.copyProperties(po, vo);
        return vo;
    }

    public static List<AuthMenuVO> posToVos(List<AuthMenuPO> dataList) {
        if (CollUtil.isNotEmpty(dataList)) {
            List<AuthMenuVO> dtoList = new ArrayList<>();
            for (AuthMenuPO po : dataList) {
                dtoList.add(poToVo(po));
            }
            return dtoList;
        }
        return null;
    }
}