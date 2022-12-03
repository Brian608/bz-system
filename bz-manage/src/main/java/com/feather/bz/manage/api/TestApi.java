package com.feather.bz.manage.api;

import com.feather.bz.common.core.JsonResult;
import com.feather.bz.manage.annoation.Log;
import com.feather.bz.manage.domain.dto.LoginDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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


   // @Log(description = "测试接口")
    @ApiOperation(value = "测试接口", notes = "测试接口", httpMethod = "POST")
    @PostMapping("/hello")
    public JsonResult<java.lang.String> hello(@RequestParam (name = "id")String id){
       return  JsonResult.buildSuccess(id);

    }
}
