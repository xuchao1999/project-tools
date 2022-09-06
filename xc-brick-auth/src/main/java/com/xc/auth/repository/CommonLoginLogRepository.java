package com.xc.auth.repository;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xc.auth.entity.po.CommonLoginLogPO;
import com.xc.auth.entity.dto.req.CommonLoginLogQueryDTO;
import com.xc.auth.entity.dto.req.CommonLoginLogSaveDTO;
import com.xc.auth.entity.po.CommonLoginLogPO;
import com.xc.auth.entity.vo.CommonLoginLogVO;
import com.xc.auth.mapper.ICommonLoginLogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.plugins.Page;


/**
* 字典名
*
* @author xc
* @date 2022-09-06 17:08:54
*/
@RequiredArgsConstructor
@Component
public class CommonLoginLogRepository extends ServiceImpl<ICommonLoginLogMapper, CommonLoginLogPO> {

    private final ICommonLoginLogMapper commonLoginLogMapper;

    public Page<CommonLoginLogVO> pageQuery(CommonLoginLogQueryDTO queryDTO) {
        Page page = new Page();
        page.setCurrent(queryDTO.getPageNum());
        page.setSize(queryDTO.getPageSize());
        Page<CommonLoginLogVO> dataList = commonLoginLogMapper.pageQuery(page, queryDTO);
        return dataList;
    }

    public static CommonLoginLogPO saveModelToPo(CommonLoginLogSaveDTO saveDTO) {
        CommonLoginLogPO po = new CommonLoginLogPO();
        BeanUtil.copyProperties(saveDTO, po);
        return po;
    }

    public static List<CommonLoginLogPO> saveModelsToPos(List<CommonLoginLogSaveDTO> saveModelList) {
        if (CollUtil.isNotEmpty(saveModelList)) {
            List<CommonLoginLogPO> dataList = new ArrayList<>();
            for (CommonLoginLogSaveDTO saveDTO : saveModelList) {
                dataList.add(saveModelToPo(saveDTO));
            }
            return dataList;
        }
        return null;
    }

    public static CommonLoginLogVO poToVo(CommonLoginLogPO po) {
        CommonLoginLogVO vo = new CommonLoginLogVO();
        BeanUtil.copyProperties(po, vo);
        return vo;
    }

    public static List<CommonLoginLogVO> posToVos(List<CommonLoginLogPO> dataList) {
        if (CollUtil.isNotEmpty(dataList)) {
            List<CommonLoginLogVO> dtoList = new ArrayList<>();
            for (CommonLoginLogPO po : dataList) {
                dtoList.add(poToVo(po));
            }
            return dtoList;
        }
        return null;
    }
}