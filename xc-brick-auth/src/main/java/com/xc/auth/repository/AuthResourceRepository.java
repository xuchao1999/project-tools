package com.xc.auth.repository;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xc.auth.entity.po.AuthResourcePO;
import com.xc.auth.entity.dto.req.AuthResourceQueryDTO;
import com.xc.auth.entity.dto.req.AuthResourceSaveDTO;
import com.xc.auth.entity.po.AuthResourcePO;
import com.xc.auth.entity.vo.AuthResourceVO;
import com.xc.auth.mapper.IAuthResourceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.plugins.Page;


/**
* 资源表
*
* @author xc
* @date 2022-09-06 17:08:54
*/
@RequiredArgsConstructor
@Component
public class AuthResourceRepository extends ServiceImpl<IAuthResourceMapper, AuthResourcePO> {

    private final IAuthResourceMapper authResourceMapper;

    public Page<AuthResourceVO> pageQuery(AuthResourceQueryDTO queryDTO) {
        Page page = new Page();
        page.setCurrent(queryDTO.getPageNum());
        page.setSize(queryDTO.getPageSize());
        Page<AuthResourceVO> dataList = authResourceMapper.pageQuery(page, queryDTO);
        return dataList;
    }

    public static AuthResourcePO saveModelToPo(AuthResourceSaveDTO saveDTO) {
        AuthResourcePO po = new AuthResourcePO();
        BeanUtil.copyProperties(saveDTO, po);
        return po;
    }

    public static List<AuthResourcePO> saveModelsToPos(List<AuthResourceSaveDTO> saveModelList) {
        if (CollUtil.isNotEmpty(saveModelList)) {
            List<AuthResourcePO> dataList = new ArrayList<>();
            for (AuthResourceSaveDTO saveDTO : saveModelList) {
                dataList.add(saveModelToPo(saveDTO));
            }
            return dataList;
        }
        return null;
    }

    public static AuthResourceVO poToVo(AuthResourcePO po) {
        AuthResourceVO vo = new AuthResourceVO();
        BeanUtil.copyProperties(po, vo);
        return vo;
    }

    public static List<AuthResourceVO> posToVos(List<AuthResourcePO> dataList) {
        if (CollUtil.isNotEmpty(dataList)) {
            List<AuthResourceVO> dtoList = new ArrayList<>();
            for (AuthResourcePO po : dataList) {
                dtoList.add(poToVo(po));
            }
            return dtoList;
        }
        return null;
    }
}