package com.xc.auth.controller;

import com.xc.auth.entity.dto.req.AuthRoleQueryDTO;
import com.xc.auth.entity.dto.req.AuthRoleSaveDTO;
import com.xc.auth.entity.vo.AuthRoleVO;
import com.xc.auth.service.IAuthRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.xc.core.base.R;
import java.util.List;

/**
 * 角色表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@Api(tags = "角色表")
@RequiredArgsConstructor
@RequestMapping(value = "/authrole")
@RestController
public class AuthRoleController {


    private final IAuthRoleService authRoleService;

    @ApiOperation(value = "保存")
    @PostMapping
    public R save(@Validated @RequestBody AuthRoleSaveDTO saveDTO) {
        authRoleService.save(saveDTO);
        return R.success();
    }

    @ApiOperation("修改")
    @PutMapping
    public R updateById(@Validated @RequestBody AuthRoleSaveDTO saveDTO) {
        authRoleService.updateById(saveDTO);
        return R.success();
    }

    @ApiOperation("分页列表")
    @GetMapping
    public R<Page<AuthRoleVO>> pageQuery(AuthRoleQueryDTO queryDTO) {
        Page<AuthRoleVO> list = authRoleService.pageQuery(queryDTO);
        return R.success(list);
    }


    @ApiOperation("根据ID查询记录")
    @GetMapping(value = "/{id}")
    public R<AuthRoleVO> getOneById(@PathVariable Long id) {
        AuthRoleVO result = authRoleService.getOneById(id);
        return R.success(result);
    }

    @ApiOperation("删除")
    @DeleteMapping(value = "/{id}")
    public R delFlagById(@PathVariable Long id) {
        authRoleService.delFlagById(id);
        return R.success();
    }

    @ApiOperation("批量删除")
    @DeleteMapping
    public R delFlagBatch(@RequestBody List<Long> idList) {
        authRoleService.delFlagBatch(idList);
        return R.success();
    }
}