package com.xc.auth.mapper;

import com.xc.auth.entity.dto.req.AuthMenuQueryDTO;
import com.xc.auth.entity.po.AuthMenuPO;
import com.xc.auth.entity.vo.AuthMenuVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 菜单表
 *
 * @author xc
 * @date 2022-09-01 11:13:24
 */
@Mapper
public interface IAuthMenuMapper extends BaseMapper<AuthMenuPO> {

    /**
     * 分页查询
     *
     * @author xc
     * @date 2022/09/01
     **/
    Page<AuthMenuVO> pageQuery(Page page, AuthMenuQueryDTO queryDTO);

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