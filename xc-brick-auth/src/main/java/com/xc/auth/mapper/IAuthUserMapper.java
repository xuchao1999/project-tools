package com.xc.auth.mapper;

import com.xc.auth.entity.dto.req.AuthUserQueryDTO;
import com.xc.auth.entity.po.AuthUserPO;
import com.xc.auth.entity.vo.AuthUserVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 用户表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@Mapper
public interface IAuthUserMapper extends BaseMapper<AuthUserPO> {

    /**
     * 分页查询
     *
     * @author xc
     * @date 2022/09/06
     **/
    Page<AuthUserVO> pageQuery(Page page, AuthUserQueryDTO queryDTO);

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