package com.feather.bz.manage.api;


import com.feather.bz.common.core.JsonResult;
import com.feather.bz.manage.annoation.Log;
import com.feather.bz.manage.domain.SysLog;
import com.feather.bz.manage.service.ISysLogService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author feather
 * @since 2022-11-27
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/sys-log")
public class SysLogController {

    private final   ISysLogService sysLogService;

    @ApiOperation(value = "日志列表",httpMethod = "GET", produces = "application/json")
    @GetMapping("/logList")
    public JsonResult<List<SysLog>> logList() {
        return JsonResult.buildSuccess(sysLogService.list());
    }

}

