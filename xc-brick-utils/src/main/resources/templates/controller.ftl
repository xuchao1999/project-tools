<#if classInfo.moduleName?length gt 0>
package ${classInfo.packageName}.${classInfo.moduleName}.controller;
<#else>
package ${classInfo.packageName}.controller;
</#if>

<#if classInfo.moduleName?length gt 0>
import ${classInfo.packageName}.${classInfo.moduleName}.entity.dto.req.${classInfo.className}QueryDTO;
import ${classInfo.packageName}.${classInfo.moduleName}.entity.dto.req.${classInfo.className}SaveDTO;
import ${classInfo.packageName}.${classInfo.moduleName}.entity.vo.${classInfo.className}VO;
import ${classInfo.packageName}.${classInfo.moduleName}.service.I${classInfo.className}Service;
<#else>
import ${classInfo.packageName}.entity.dto.req.${classInfo.className}QueryDTO;
import ${classInfo.packageName}.entity.dto.req.${classInfo.className}SaveDTO;
import ${classInfo.packageName}.entity.vo.${classInfo.className}VO;
import ${classInfo.packageName}.service.I${classInfo.className}Service;
</#if>
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.xc.core.base.R;
import java.util.List;

/**
 * ${classInfo.classComment}
 *
 * @author ${authorName}
 * @date ${.now?string('yyyy-MM-dd HH:mm:ss')}
 */
@Api(tags = "${classInfo.classComment}")
@RequiredArgsConstructor
@RequestMapping(value = "/${classInfo.className?lower_case}")
@RestController
public class ${classInfo.className}Controller {


    private final I${classInfo.className}Service ${classInfo.className?uncap_first}Service;

    @ApiOperation(value = "保存")
    @PostMapping
    public R save(@Validated @RequestBody ${classInfo.className}SaveDTO saveDTO) {
        ${classInfo.className?uncap_first}Service.save(saveDTO);
        return R.success();
    }

    @ApiOperation("修改")
    @PutMapping
    public R updateById(@Validated @RequestBody ${classInfo.className}SaveDTO saveDTO) {
        ${classInfo.className?uncap_first}Service.updateById(saveDTO);
        return R.success();
    }

    @ApiOperation("分页列表")
    @GetMapping
    public R<Page<${classInfo.className}VO>> pageQuery(${classInfo.className}QueryDTO queryDTO) {
        Page<${classInfo.className}VO> list = ${classInfo.className?uncap_first}Service.pageQuery(queryDTO);
        return R.success(list);
    }


    @ApiOperation("根据ID查询记录")
    @GetMapping(value = "/{id}")
    public R<${classInfo.className}VO> getOneById(@PathVariable Long id) {
        ${classInfo.className}VO result = ${classInfo.className?uncap_first}Service.getOneById(id);
        return R.success(result);
    }

    @ApiOperation("删除")
    @DeleteMapping(value = "/{id}")
    public R delFlagById(@PathVariable Long id) {
        ${classInfo.className?uncap_first}Service.delFlagById(id);
        return R.success();
    }

    @ApiOperation("批量删除")
    @DeleteMapping
    public R delFlagBatch(@RequestBody List<Long> idList) {
        ${classInfo.className?uncap_first}Service.delFlagBatch(idList);
        return R.success();
    }
}