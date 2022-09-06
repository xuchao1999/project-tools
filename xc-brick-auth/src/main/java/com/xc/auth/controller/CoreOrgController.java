package com.xc.auth.controller;

import com.xc.auth.entity.dto.req.CoreOrgQueryDTO;
import com.xc.auth.entity.dto.req.CoreOrgSaveDTO;
import com.xc.auth.entity.vo.CoreOrgVO;
import com.xc.auth.service.ICoreOrgService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.xc.core.base.R;
import java.util.List;

/**
 * 组织表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@Api(tags = "组织表")
@RequiredArgsConstructor
@RequestMapping(value = "/coreorg")
@RestController
public class CoreOrgController {


    private final ICoreOrgService coreOrgService;

    @ApiOperation(value = "保存")
    @PostMapping
    public R save(@Validated @RequestBody CoreOrgSaveDTO saveDTO) {
        coreOrgService.save(saveDTO);
        return R.success();
    }

    @ApiOperation("修改")
    @PutMapping
    public R updateById(@Validated @RequestBody CoreOrgSaveDTO saveDTO) {
        coreOrgService.updateById(saveDTO);
        return R.success();
    }

    @ApiOperation("分页列表")
    @GetMapping
    public R<Page<CoreOrgVO>> pageQuery(CoreOrgQueryDTO queryDTO) {
        Page<CoreOrgVO> list = coreOrgService.pageQuery(queryDTO);
        return R.success(list);
    }


    @ApiOperation("根据ID查询记录")
    @GetMapping(value = "/{id}")
    public R<CoreOrgVO> getOneById(@PathVariable Long id) {
        CoreOrgVO result = coreOrgService.getOneById(id);
        return R.success(result);
    }

    @ApiOperation("删除")
    @DeleteMapping(value = "/{id}")
    public R delFlagById(@PathVariable Long id) {
        coreOrgService.delFlagById(id);
        return R.success();
    }

    @ApiOperation("批量删除")
    @DeleteMapping
    public R delFlagBatch(@RequestBody List<Long> idList) {
        coreOrgService.delFlagBatch(idList);
        return R.success();
    }
}