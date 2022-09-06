package com.xc.auth.service;

import com.xc.auth.entity.dto.req.CommonLoginLogQueryDTO;
import com.xc.auth.entity.dto.req.CommonLoginLogSaveDTO;
import com.xc.auth.entity.vo.CommonLoginLogVO;
import com.baomidou.mybatisplus.plugins.Page;
import java.util.List;

/**
 * 字典名
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
public interface ICommonLoginLogService {

    /**
     * 保存（添加或修改）
     *
     * @param saveDTO
     * @return
     * @author xc
     */
    void save(CommonLoginLogSaveDTO saveDTO);

    /**
     * 修改
     *
     * @param saveDTO
     * @return
     * @author xc
     */
    void updateById(CommonLoginLogSaveDTO saveDTO);

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
    Page<CommonLoginLogVO> pageQuery(CommonLoginLogQueryDTO queryDTO);

    /**
    * 根据ID查询记录
    *
    * @param id
    * @return
    * @author xc
    */
    CommonLoginLogVO getOneById(Long id);
}