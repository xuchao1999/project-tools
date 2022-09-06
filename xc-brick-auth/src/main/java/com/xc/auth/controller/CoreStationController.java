package com.xc.auth.controller;

import com.xc.auth.entity.dto.req.CoreStationQueryDTO;
import com.xc.auth.entity.dto.req.CoreStationSaveDTO;
import com.xc.auth.entity.vo.CoreStationVO;
import com.xc.auth.service.ICoreStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.xc.core.base.R;
import java.util.List;

/**
 * 岗位表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@Api(tags = "岗位表")
@RequiredArgsConstructor
@RequestMapping(value = "/corestation")
@RestController
public class CoreStationController {


    private final ICoreStationService coreStationService;

    @ApiOperation(value = "保存")
    @PostMapping
    public R save(@Validated @RequestBody CoreStationSaveDTO saveDTO) {
        coreStationService.save(saveDTO);
        return R.success();
    }

    @ApiOperation("修改")
    @PutMapping
    public R updateById(@Validated @RequestBody CoreStationSaveDTO saveDTO) {
        coreStationService.updateById(saveDTO);
        return R.success();
    }

    @ApiOperation("分页列表")
    @GetMapping
    public R<Page<CoreStationVO>> pageQuery(CoreStationQueryDTO queryDTO) {
        Page<CoreStationVO> list = coreStationService.pageQuery(queryDTO);
        return R.success(list);
    }


    @ApiOperation("根据ID查询记录")
    @GetMapping(value = "/{id}")
    public R<CoreStationVO> getOneById(@PathVariable Long id) {
        CoreStationVO result = coreStationService.getOneById(id);
        return R.success(result);
    }

    @ApiOperation("删除")
    @DeleteMapping(value = "/{id}")
    public R delFlagById(@PathVariable Long id) {
        coreStationService.delFlagById(id);
        return R.success();
    }

    @ApiOperation("批量删除")
    @DeleteMapping
    public R delFlagBatch(@RequestBody List<Long> idList) {
        coreStationService.delFlagBatch(idList);
        return R.success();
    }
}