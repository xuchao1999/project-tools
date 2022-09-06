package com.xc.auth.controller;

import com.xc.auth.entity.dto.req.AuthRoleAuthorityQueryDTO;
import com.xc.auth.entity.dto.req.AuthRoleAuthoritySaveDTO;
import com.xc.auth.entity.vo.AuthRoleAuthorityVO;
import com.xc.auth.service.IAuthRoleAuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.xc.core.base.R;
import java.util.List;

/**
 * 角色权限关系表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@Api(tags = "角色权限关系表")
@RequiredArgsConstructor
@RequestMapping(value = "/authroleauthority")
@RestController
public class AuthRoleAuthorityController {


    private final IAuthRoleAuthorityService authRoleAuthorityService;

    @ApiOperation(value = "保存")
    @PostMapping
    public R save(@Validated @RequestBody AuthRoleAuthoritySaveDTO saveDTO) {
        authRoleAuthorityService.save(saveDTO);
        return R.success();
    }

    @ApiOperation("修改")
    @PutMapping
    public R updateById(@Validated @RequestBody AuthRoleAuthoritySaveDTO saveDTO) {
        authRoleAuthorityService.updateById(saveDTO);
        return R.success();
    }

    @ApiOperation("分页列表")
    @GetMapping
    public R<Page<AuthRoleAuthorityVO>> pageQuery(AuthRoleAuthorityQueryDTO queryDTO) {
        Page<AuthRoleAuthorityVO> list = authRoleAuthorityService.pageQuery(queryDTO);
        return R.success(list);
    }


    @ApiOperation("根据ID查询记录")
    @GetMapping(value = "/{id}")
    public R<AuthRoleAuthorityVO> getOneById(@PathVariable Long id) {
        AuthRoleAuthorityVO result = authRoleAuthorityService.getOneById(id);
        return R.success(result);
    }

    @ApiOperation("删除")
    @DeleteMapping(value = "/{id}")
    public R delFlagById(@PathVariable Long id) {
        authRoleAuthorityService.delFlagById(id);
        return R.success();
    }

    @ApiOperation("批量删除")
    @DeleteMapping
    public R delFlagBatch(@RequestBody List<Long> idList) {
        authRoleAuthorityService.delFlagBatch(idList);
        return R.success();
    }
}