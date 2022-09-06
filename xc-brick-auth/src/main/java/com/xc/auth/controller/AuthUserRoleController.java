package com.xc.auth.controller;

import com.xc.auth.entity.dto.req.AuthUserRoleQueryDTO;
import com.xc.auth.entity.dto.req.AuthUserRoleSaveDTO;
import com.xc.auth.entity.vo.AuthUserRoleVO;
import com.xc.auth.service.IAuthUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.xc.core.base.R;
import java.util.List;

/**
 * 用户角色关系
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@Api(tags = "用户角色关系")
@RequiredArgsConstructor
@RequestMapping(value = "/authuserrole")
@RestController
public class AuthUserRoleController {


    private final IAuthUserRoleService authUserRoleService;

    @ApiOperation(value = "保存")
    @PostMapping
    public R save(@Validated @RequestBody AuthUserRoleSaveDTO saveDTO) {
        authUserRoleService.save(saveDTO);
        return R.success();
    }

    @ApiOperation("修改")
    @PutMapping
    public R updateById(@Validated @RequestBody AuthUserRoleSaveDTO saveDTO) {
        authUserRoleService.updateById(saveDTO);
        return R.success();
    }

    @ApiOperation("分页列表")
    @GetMapping
    public R<Page<AuthUserRoleVO>> pageQuery(AuthUserRoleQueryDTO queryDTO) {
        Page<AuthUserRoleVO> list = authUserRoleService.pageQuery(queryDTO);
        return R.success(list);
    }


    @ApiOperation("根据ID查询记录")
    @GetMapping(value = "/{id}")
    public R<AuthUserRoleVO> getOneById(@PathVariable Long id) {
        AuthUserRoleVO result = authUserRoleService.getOneById(id);
        return R.success(result);
    }

    @ApiOperation("删除")
    @DeleteMapping(value = "/{id}")
    public R delFlagById(@PathVariable Long id) {
        authUserRoleService.delFlagById(id);
        return R.success();
    }

    @ApiOperation("批量删除")
    @DeleteMapping
    public R delFlagBatch(@RequestBody List<Long> idList) {
        authUserRoleService.delFlagBatch(idList);
        return R.success();
    }
}