package com.xc.auth.service;

import com.xc.auth.entity.dto.req.AuthResourceQueryDTO;
import com.xc.auth.entity.dto.req.AuthResourceSaveDTO;
import com.xc.auth.entity.vo.AuthResourceVO;
import com.baomidou.mybatisplus.plugins.Page;
import java.util.List;

/**
 * 资源表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
public interface IAuthResourceService {

    /**
     * 保存（添加或修改）
     *
     * @param saveDTO
     * @return
     * @author xc
     */
    void save(AuthResourceSaveDTO saveDTO);

    /**
     * 修改
     *
     * @param saveDTO
     * @return
     * @author xc
     */
    void updateById(AuthResourceSaveDTO saveDTO);

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
    Page<AuthResourceVO> pageQuery(AuthResourceQueryDTO queryDTO);

    /**
    * 根据ID查询记录
    *
    * @param id
    * @return
    * @author xc
    */
    AuthResourceVO getOneById(Long id);
}