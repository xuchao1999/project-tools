package com.xc.auth.controller;

import com.xc.auth.entity.dto.req.AuthRoleOrgQueryDTO;
import com.xc.auth.entity.dto.req.AuthRoleOrgSaveDTO;
import com.xc.auth.entity.vo.AuthRoleOrgVO;
import com.xc.auth.service.IAuthRoleOrgService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.xc.core.base.R;
import java.util.List;

/**
 * 角色组织关系表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@Api(tags = "角色组织关系表")
@RequiredArgsConstructor
@RequestMapping(value = "/authroleorg")
@RestController
public class AuthRoleOrgController {


    private final IAuthRoleOrgService authRoleOrgService;

    @ApiOperation(value = "保存")
    @PostMapping
    public R save(@Validated @RequestBody AuthRoleOrgSaveDTO saveDTO) {
        authRoleOrgService.save(saveDTO);
        return R.success();
    }

    @ApiOperation("修改")
    @PutMapping
    public R updateById(@Validated @RequestBody AuthRoleOrgSaveDTO saveDTO) {
        authRoleOrgService.updateById(saveDTO);
        return R.success();
    }

    @ApiOperation("分页列表")
    @GetMapping
    public R<Page<AuthRoleOrgVO>> pageQuery(AuthRoleOrgQueryDTO queryDTO) {
        Page<AuthRoleOrgVO> list = authRoleOrgService.pageQuery(queryDTO);
        return R.success(list);
    }


    @ApiOperation("根据ID查询记录")
    @GetMapping(value = "/{id}")
    public R<AuthRoleOrgVO> getOneById(@PathVariable Long id) {
        AuthRoleOrgVO result = authRoleOrgService.getOneById(id);
        return R.success(result);
    }

    @ApiOperation("删除")
    @DeleteMapping(value = "/{id}")
    public R delFlagById(@PathVariable Long id) {
        authRoleOrgService.delFlagById(id);
        return R.success();
    }

    @ApiOperation("批量删除")
    @DeleteMapping
    public R delFlagBatch(@RequestBody List<Long> idList) {
        authRoleOrgService.delFlagBatch(idList);
        return R.success();
    }
}