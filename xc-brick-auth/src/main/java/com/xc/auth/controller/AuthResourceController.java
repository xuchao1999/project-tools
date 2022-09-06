package com.xc.auth.controller;

import com.xc.auth.entity.dto.req.AuthResourceQueryDTO;
import com.xc.auth.entity.dto.req.AuthResourceSaveDTO;
import com.xc.auth.entity.vo.AuthResourceVO;
import com.xc.auth.service.IAuthResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.xc.core.base.R;
import java.util.List;

/**
 * 资源表
 *
 * @author xc
 * @date 2022-09-06 17:08:54
 */
@Api(tags = "资源表")
@RequiredArgsConstructor
@RequestMapping(value = "/authresource")
@RestController
public class AuthResourceController {


    private final IAuthResourceService authResourceService;

    @ApiOperation(value = "保存")
    @PostMapping
    public R save(@Validated @RequestBody AuthResourceSaveDTO saveDTO) {
        authResourceService.save(saveDTO);
        return R.success();
    }

    @ApiOperation("修改")
    @PutMapping
    public R updateById(@Validated @RequestBody AuthResourceSaveDTO saveDTO) {
        authResourceService.updateById(saveDTO);
        return R.success();
    }

    @ApiOperation("分页列表")
    @GetMapping
    public R<Page<AuthResourceVO>> pageQuery(AuthResourceQueryDTO queryDTO) {
        Page<AuthResourceVO> list = authResourceService.pageQuery(queryDTO);
        return R.success(list);
    }


    @ApiOperation("根据ID查询记录")
    @GetMapping(value = "/{id}")
    public R<AuthResourceVO> getOneById(@PathVariable Long id) {
        AuthResourceVO result = authResourceService.getOneById(id);
        return R.success(result);
    }

    @ApiOperation("删除")
    @DeleteMapping(value = "/{id}")
    public R delFlagById(@PathVariable Long id) {
        authResourceService.delFlagById(id);
        return R.success();
    }

    @ApiOperation("批量删除")
    @DeleteMapping
    public R delFlagBatch(@RequestBody List<Long> idList) {
        authResourceService.delFlagBatch(idList);
        return R.success();
    }
}