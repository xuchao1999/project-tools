package com.xc.auth.mapper;

import com.xc.auth.entity.dto.req.CoreOrgQueryDTO;
import com.xc.auth.entity.po.CoreOrgPO;
import com.xc.auth.entity.vo.CoreOrgVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 组织表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@Mapper
public interface ICoreOrgMapper extends BaseMapper<CoreOrgPO> {

    /**
     * 分页查询
     *
     * @author xc
     * @date 2022/09/06
     **/
    Page<CoreOrgVO> pageQuery(Page page, CoreOrgQueryDTO queryDTO);

    /**
     * 逻辑删除
     *
     * @param id
     * @param userId
     * @return
     */
    int logicDelete(@Param("id") Long id, @Param("updateBy") Long userId);

    /**
     * 批量逻辑删除
     *
     * @param idList
     * @param userId
     * @return
     */
    int logicDeleteBatch(@Param("idList") List<Long> idList, @Param("updateBy") Long userId);
}