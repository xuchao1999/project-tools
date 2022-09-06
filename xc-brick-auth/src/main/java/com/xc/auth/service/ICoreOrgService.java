package com.xc.auth.service;

import com.xc.auth.entity.dto.req.CoreOrgQueryDTO;
import com.xc.auth.entity.dto.req.CoreOrgSaveDTO;
import com.xc.auth.entity.vo.CoreOrgVO;
import com.baomidou.mybatisplus.plugins.Page;
import java.util.List;

/**
 * 组织表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
public interface ICoreOrgService {

    /**
     * 保存（添加或修改）
     *
     * @param saveDTO
     * @return
     * @author xc
     */
    void save(CoreOrgSaveDTO saveDTO);

    /**
     * 修改
     *
     * @param saveDTO
     * @return
     * @author xc
     */
    void updateById(CoreOrgSaveDTO saveDTO);

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
    Page<CoreOrgVO> pageQuery(CoreOrgQueryDTO queryDTO);

    /**
    * 根据ID查询记录
    *
    * @param id
    * @return
    * @author xc
    */
    CoreOrgVO getOneById(Long id);
}