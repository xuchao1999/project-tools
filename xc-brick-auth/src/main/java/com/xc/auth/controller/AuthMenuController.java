package com.xc.auth.controller;

import com.xc.auth.entity.dto.req.AuthMenuQueryDTO;
import com.xc.auth.entity.dto.req.AuthMenuSaveDTO;
import com.xc.auth.entity.vo.AuthMenuVO;
import com.xc.auth.service.IAuthMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.xc.core.base.R;
import java.util.List;

/**
 * 菜单表
 *
 * @author xc
 * @date 2022-09-01 11:13:24
 */
@Api(tags = "菜单表")
@RequiredArgsConstructor
@RequestMapping(value = "/authmenu")
@RestController
public class AuthMenuController {


    private final IAuthMenuService authMenuService;

    @ApiOperation(value = "保存")
    @PostMapping
    public R save(@Validated @RequestBody AuthMenuSaveDTO saveDTO) {
        authMenuService.save(saveDTO);
        return R.success();
    }

    @ApiOperation("修改")
    @PutMapping
    public R updateById(@Validated @RequestBody AuthMenuSaveDTO saveDTO) {
        authMenuService.updateById(saveDTO);
        return R.success();
    }

    @ApiOperation("分页列表")
    @GetMapping
    public R<Page<AuthMenuVO>> pageQuery(AuthMenuQueryDTO queryDTO) {
        Page<AuthMenuVO> list = authMenuService.pageQuery(queryDTO);
        return R.success(list);
    }


    @ApiOperation("根据ID查询记录")
    @GetMapping(value = "/{id}")
    public R<AuthMenuVO> getOneById(@PathVariable Long id) {
        AuthMenuVO result = authMenuService.getOneById(id);
        return R.success(result);
    }

    @ApiOperation("删除")
    @DeleteMapping(value = "/{id}")
    public R delFlagById(@PathVariable Long id) {
        authMenuService.delFlagById(id);
        return R.success();
    }

    @ApiOperation("批量删除")
    @DeleteMapping
    public R delFlagBatch(@RequestBody List<Long> idList) {
        authMenuService.delFlagBatch(idList);
        return R.success();
    }
}