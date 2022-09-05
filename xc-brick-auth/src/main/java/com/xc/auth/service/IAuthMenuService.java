package com.xc.auth.service;

import com.xc.auth.entity.dto.req.AuthMenuQueryDTO;
import com.xc.auth.entity.dto.req.AuthMenuSaveDTO;
import com.xc.auth.entity.vo.AuthMenuVO;
import com.baomidou.mybatisplus.plugins.Page;
import java.util.List;

/**
 * 菜单表
 *
 * @author xc
 * @date 2022-09-01 11:13:24
 */
public interface IAuthMenuService {

    /**
     * 保存（添加或修改）
     *
     * @param saveDTO
     * @return
     * @author xc
     */
    void save(AuthMenuSaveDTO saveDTO);

    /**
     * 修改
     *
     * @param saveDTO
     * @return
     * @author xc
     */
    void updateById(AuthMenuSaveDTO saveDTO);

    /**
     * 根据ID逻辑删除记录
     *
     * @param id
     * @return
     * @author xc
     */
    void delFlagById(Long id);

    /**
     * 根据ID逻辑删除记录（批量)
     *
     * @param idList
     * @return
     * @author xc
     */
    void delFlagBatch(List<Long> idList);

    /**
     * 分页查询
     *
     * @param queryDTO
     * @return
     * @author xc
     */
    Page<AuthMenuVO> pageQuery(AuthMenuQueryDTO queryDTO);

    /**
    * 根据ID查询记录
    *
    * @param id
    * @return
    * @author xc
    */
    AuthMenuVO getOneById(Long id);
}