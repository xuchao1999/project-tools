package com.xc.auth.repository;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xc.auth.entity.po.CoreStationPO;
import com.xc.auth.entity.dto.req.CoreStationQueryDTO;
import com.xc.auth.entity.dto.req.CoreStationSaveDTO;
import com.xc.auth.entity.po.CoreStationPO;
import com.xc.auth.entity.vo.CoreStationVO;
import com.xc.auth.mapper.ICoreStationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.plugins.Page;


/**
* 岗位表
*
* @author xc
* @date 2022-09-06 17:08:54
*/
@RequiredArgsConstructor
@Component
public class CoreStationRepository extends ServiceImpl<ICoreStationMapper, CoreStationPO> {

    private final ICoreStationMapper coreStationMapper;

    public Page<CoreStationVO> pageQuery(CoreStationQueryDTO queryDTO) {
        Page page = new Page();
        page.setCurrent(queryDTO.getPageNum());
        page.setSize(queryDTO.getPageSize());
        Page<CoreStationVO> dataList = coreStationMapper.pageQuery(page, queryDTO);
        return dataList;
    }

    public static CoreStationPO saveModelToPo(CoreStationSaveDTO saveDTO) {
        CoreStationPO po = new CoreStationPO();
        BeanUtil.copyProperties(saveDTO, po);
        return po;
    }

    public static List<CoreStationPO> saveModelsToPos(List<CoreStationSaveDTO> saveModelList) {
        if (CollUtil.isNotEmpty(saveModelList)) {
            List<CoreStationPO> dataList = new ArrayList<>();
            for (CoreStationSaveDTO saveDTO : saveModelList) {
                dataList.add(saveModelToPo(saveDTO));
            }
            return dataList;
        }
        return null;
    }

    public static CoreStationVO poToVo(CoreStationPO po) {
        CoreStationVO vo = new CoreStationVO();
        BeanUtil.copyProperties(po, vo);
        return vo;
    }

    public static List<CoreStationVO> posToVos(List<CoreStationPO> dataList) {
        if (CollUtil.isNotEmpty(dataList)) {
            List<CoreStationVO> dtoList = new ArrayList<>();
            for (CoreStationPO po : dataList) {
                dtoList.add(poToVo(po));
            }
            return dtoList;
        }
        return null;
    }
}