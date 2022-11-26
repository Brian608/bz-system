package com.feather.manage.api;

import com.feather.bz.common.core.JsonResult;
import com.feather.bz.common.enums.ErrorCodeEnum;
import com.feather.bz.common.exception.BaseBizException;
import com.feather.bz.common.exception.CommonErrorCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: bz-system
 * @package: com.feather.manage.api
 * @className: TestApi
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022-11-25 23:13
 * @version: 1.0
  **/
@Api(value = "测试", tags = {"测试"})
@RequestMapping("/test")
@RestController
public class TestApi {


    @ApiOperation(value = "测试接口", notes = "测试接口", httpMethod = "GET")
    @GetMapping("/hello")
    public JsonResult<String> test(){
       return  JsonResult.buildSuccess();

    }
}
