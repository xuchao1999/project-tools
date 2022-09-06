package com.xc.auth.controller;

import com.xc.auth.entity.dto.req.AuthUserQueryDTO;
import com.xc.auth.entity.dto.req.AuthUserSaveDTO;
import com.xc.auth.entity.vo.AuthUserVO;
import com.xc.auth.service.IAuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.xc.core.base.R;
import java.util.List;

/**
 * 用户表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@Api(tags = "用户表")
@RequiredArgsConstructor
@RequestMapping(value = "/authuser")
@RestController
public class AuthUserController {


    private final IAuthUserService authUserService;

    @ApiOperation(value = "保存")
    @PostMapping
    public R save(@Validated @RequestBody AuthUserSaveDTO saveDTO) {
        authUserService.save(saveDTO);
        return R.success();
    }

    @ApiOperation("修改")
    @PutMapping
    public R updateById(@Validated @RequestBody AuthUserSaveDTO saveDTO) {
        authUserService.updateById(saveDTO);
        return R.success();
    }

    @ApiOperation("分页列表")
    @GetMapping
    public R<Page<AuthUserVO>> pageQuery(AuthUserQueryDTO queryDTO) {
        Page<AuthUserVO> list = authUserService.pageQuery(queryDTO);
        return R.success(list);
    }


    @ApiOperation("根据ID查询记录")
    @GetMapping(value = "/{id}")
    public R<AuthUserVO> getOneById(@PathVariable Long id) {
        AuthUserVO result = authUserService.getOneById(id);
        return R.success(result);
    }

    @ApiOperation("删除")
    @DeleteMapping(value = "/{id}")
    public R delFlagById(@PathVariable Long id) {
        authUserService.delFlagById(id);
        return R.success();
    }

    @ApiOperation("批量删除")
    @DeleteMapping
    public R delFlagBatch(@RequestBody List<Long> idList) {
        authUserService.delFlagBatch(idList);
        return R.success();
    }
}