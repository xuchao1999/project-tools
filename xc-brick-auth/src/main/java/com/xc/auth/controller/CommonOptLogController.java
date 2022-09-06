package com.xc.auth.controller;

import com.xc.auth.entity.dto.req.CommonOptLogQueryDTO;
import com.xc.auth.entity.dto.req.CommonOptLogSaveDTO;
import com.xc.auth.entity.vo.CommonOptLogVO;
import com.xc.auth.service.ICommonOptLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.xc.core.base.R;
import java.util.List;

/**
 * 操作日志表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@Api(tags = "操作日志表")
@RequiredArgsConstructor
@RequestMapping(value = "/commonoptlog")
@RestController
public class CommonOptLogController {


    private final ICommonOptLogService commonOptLogService;

    @ApiOperation(value = "保存")
    @PostMapping
    public R save(@Validated @RequestBody CommonOptLogSaveDTO saveDTO) {
        commonOptLogService.save(saveDTO);
        return R.success();
    }

    @ApiOperation("修改")
    @PutMapping
    public R updateById(@Validated @RequestBody CommonOptLogSaveDTO saveDTO) {
        commonOptLogService.updateById(saveDTO);
        return R.success();
    }

    @ApiOperation("分页列表")
    @GetMapping
    public R<Page<CommonOptLogVO>> pageQuery(CommonOptLogQueryDTO queryDTO) {
        Page<CommonOptLogVO> list = commonOptLogService.pageQuery(queryDTO);
        return R.success(list);
    }


    @ApiOperation("根据ID查询记录")
    @GetMapping(value = "/{id}")
    public R<CommonOptLogVO> getOneById(@PathVariable Long id) {
        CommonOptLogVO result = commonOptLogService.getOneById(id);
        return R.success(result);
    }

    @ApiOperation("删除")
    @DeleteMapping(value = "/{id}")
    public R delFlagById(@PathVariable Long id) {
        commonOptLogService.delFlagById(id);
        return R.success();
    }

    @ApiOperation("批量删除")
    @DeleteMapping
    public R delFlagBatch(@RequestBody List<Long> idList) {
        commonOptLogService.delFlagBatch(idList);
        return R.success();
    }
}