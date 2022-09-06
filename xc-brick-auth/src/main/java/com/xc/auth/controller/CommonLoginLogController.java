package com.xc.auth.controller;

import com.xc.auth.entity.dto.req.CommonLoginLogQueryDTO;
import com.xc.auth.entity.dto.req.CommonLoginLogSaveDTO;
import com.xc.auth.entity.vo.CommonLoginLogVO;
import com.xc.auth.service.ICommonLoginLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.xc.core.base.R;
import java.util.List;

/**
 * 字典名
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@Api(tags = "字典名")
@RequiredArgsConstructor
@RequestMapping(value = "/commonloginlog")
@RestController
public class CommonLoginLogController {


    private final ICommonLoginLogService commonLoginLogService;

    @ApiOperation(value = "保存")
    @PostMapping
    public R save(@Validated @RequestBody CommonLoginLogSaveDTO saveDTO) {
        commonLoginLogService.save(saveDTO);
        return R.success();
    }

    @ApiOperation("修改")
    @PutMapping
    public R updateById(@Validated @RequestBody CommonLoginLogSaveDTO saveDTO) {
        commonLoginLogService.updateById(saveDTO);
        return R.success();
    }

    @ApiOperation("分页列表")
    @GetMapping
    public R<Page<CommonLoginLogVO>> pageQuery(CommonLoginLogQueryDTO queryDTO) {
        Page<CommonLoginLogVO> list = commonLoginLogService.pageQuery(queryDTO);
        return R.success(list);
    }


    @ApiOperation("根据ID查询记录")
    @GetMapping(value = "/{id}")
    public R<CommonLoginLogVO> getOneById(@PathVariable Long id) {
        CommonLoginLogVO result = commonLoginLogService.getOneById(id);
        return R.success(result);
    }

    @ApiOperation("删除")
    @DeleteMapping(value = "/{id}")
    public R delFlagById(@PathVariable Long id) {
        commonLoginLogService.delFlagById(id);
        return R.success();
    }

    @ApiOperation("批量删除")
    @DeleteMapping
    public R delFlagBatch(@RequestBody List<Long> idList) {
        commonLoginLogService.delFlagBatch(idList);
        return R.success();
    }
}