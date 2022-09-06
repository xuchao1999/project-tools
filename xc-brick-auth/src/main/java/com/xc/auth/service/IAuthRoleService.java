package com.xc.auth.service;

import com.xc.auth.entity.dto.req.AuthRoleQueryDTO;
import com.xc.auth.entity.dto.req.AuthRoleSaveDTO;
import com.xc.auth.entity.vo.AuthRoleVO;
import com.baomidou.mybatisplus.plugins.Page;
import java.util.List;

/**
 * 角色表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
public interface IAuthRoleService {

    /**
     * 保存（添加或修改）
     *
     * @param saveDTO
     * @return
     * @author xc
     */
    void save(AuthRoleSaveDTO saveDTO);

    /**
     * 修改
     *
     * @param saveDTO
     * @return
     * @author xc
     */
    void updateById(AuthRoleSaveDTO saveDTO);

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
    Page<AuthRoleVO> pageQuery(AuthRoleQueryDTO queryDTO);

    /**
    * 根据ID查询记录
    *
    * @param id
    * @return
    * @author xc
    */
    AuthRoleVO getOneById(Long id);
}