package com.feather.bz.manage.api;


import com.feather.bz.common.constants.CoreConstant;
import com.feather.bz.common.core.JsonResult;
import com.feather.bz.manage.annoation.Log;
import com.feather.bz.manage.domain.SysUser;
import com.feather.bz.manage.domain.bo.AddUserBO;
import com.feather.bz.manage.domain.dto.LoginDTO;
import com.feather.bz.manage.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表  前端控制器
 * </p>
 *
 * @author feather
 * @since 2022-11-30
 */
@Api(value = "用户模块", tags = {"用户模块"})
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping(CoreConstant.API+CoreConstant.V1+"/sys-user")
public class SysUserController {

    private  final ISysUserService sysUserService;

    @Log(description = "注册用户")
    @ApiOperation(value = "注册用户",httpMethod = "POST", produces = "application/json")
    @PostMapping("/registerUser")
    public JsonResult<SysUser> registerUser(@RequestBody  @Validated  AddUserBO addUserBO) {
        return JsonResult.buildSuccess(sysUserService.registerUser(addUserBO));
    }


    @Log(description = "登录")
    @ApiOperation(value = "登录",httpMethod = "POST", produces = "application/json")
    @PostMapping("/login")
    public JsonResult<String> login(@RequestBody  @Validated LoginDTO loginDTO) {
        return JsonResult.buildSuccess(sysUserService.login(loginDTO));
    }

    @Log(description = "退出登录")
    @ApiOperation(value = "退出登录",httpMethod = "POST", produces = "application/json")
    @PostMapping("/logOut")
    public JsonResult<Boolean> logOut(@ApiParam(name = "userName",value = "用户名",required = true) @RequestParam String userName) {
        return JsonResult.buildSuccess(sysUserService.logOut(userName));
    }

}

