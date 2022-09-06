package com.xc.auth.repository;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xc.auth.entity.po.CommonOptLogPO;
import com.xc.auth.entity.dto.req.CommonOptLogQueryDTO;
import com.xc.auth.entity.dto.req.CommonOptLogSaveDTO;
import com.xc.auth.entity.po.CommonOptLogPO;
import com.xc.auth.entity.vo.CommonOptLogVO;
import com.xc.auth.mapper.ICommonOptLogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.plugins.Page;


/**
* 操作日志表
*
* @author xc
* @date 2022-09-06 17:08:54
*/
@RequiredArgsConstructor
@Component
public class CommonOptLogRepository extends ServiceImpl<ICommonOptLogMapper, CommonOptLogPO> {

    private final ICommonOptLogMapper commonOptLogMapper;

    public Page<CommonOptLogVO> pageQuery(CommonOptLogQueryDTO queryDTO) {
        Page page = new Page();
        page.setCurrent(queryDTO.getPageNum());
        page.setSize(queryDTO.getPageSize());
        Page<CommonOptLogVO> dataList = commonOptLogMapper.pageQuery(page, queryDTO);
        return dataList;
    }

    public static CommonOptLogPO saveModelToPo(CommonOptLogSaveDTO saveDTO) {
        CommonOptLogPO po = new CommonOptLogPO();
        BeanUtil.copyProperties(saveDTO, po);
        return po;
    }

    public static List<CommonOptLogPO> saveModelsToPos(List<CommonOptLogSaveDTO> saveModelList) {
        if (CollUtil.isNotEmpty(saveModelList)) {
            List<CommonOptLogPO> dataList = new ArrayList<>();
            for (CommonOptLogSaveDTO saveDTO : saveModelList) {
                dataList.add(saveModelToPo(saveDTO));
            }
            return dataList;
        }
        return null;
    }

    public static CommonOptLogVO poToVo(CommonOptLogPO po) {
        CommonOptLogVO vo = new CommonOptLogVO();
        BeanUtil.copyProperties(po, vo);
        return vo;
    }

    public static List<CommonOptLogVO> posToVos(List<CommonOptLogPO> dataList) {
        if (CollUtil.isNotEmpty(dataList)) {
            List<CommonOptLogVO> dtoList = new ArrayList<>();
            for (CommonOptLogPO po : dataList) {
                dtoList.add(poToVo(po));
            }
            return dtoList;
        }
        return null;
    }
}