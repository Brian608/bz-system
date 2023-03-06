package com.feather.bz.manage.api;


import com.feather.bz.common.constants.CoreConstant;
import com.feather.bz.common.core.JsonResult;
import com.feather.bz.common.core.PagingInfo;
import com.feather.bz.manage.domain.SysLog;
import com.feather.bz.manage.domain.dto.LogQueryDTO;
import com.feather.bz.manage.service.ISysLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author feather
 * @since 2022-11-27
 */
@Api(value = "日志模块", tags = {"日志模块"})
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping(CoreConstant.API+CoreConstant.V1+"/sys-log")
public class SysLogController {

    private final   ISysLogService sysLogService;

    @ApiOperation(value = "日志列表",httpMethod = "POST", produces = "application/json")
    @PostMapping("/logPage")
    public JsonResult<PagingInfo<SysLog>> logPage(@RequestBody LogQueryDTO query) {
        return JsonResult.buildSuccess(sysLogService.logPage(query));
    }
}

