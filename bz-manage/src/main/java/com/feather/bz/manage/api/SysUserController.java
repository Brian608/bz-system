package com.feather.bz.manage.api;


import com.alibaba.excel.EasyExcel;
import com.feather.bz.common.constants.CoreConstant;
import com.feather.bz.common.core.JsonResult;
import com.feather.bz.common.utils.CaptchaGenerator;
import com.feather.bz.common.utils.RandomUtil;
import com.feather.bz.manage.annoation.Log;
import com.feather.bz.manage.domain.bo.AddUserBO;
import com.feather.bz.manage.domain.dto.LoginDTO;
import com.feather.bz.manage.domain.vo.UserVO;
import com.feather.bz.manage.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

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

   // @Log(description = "注册用户")
    @ApiOperation(value = "注册用户",httpMethod = "POST", produces = "application/json")
    @PostMapping("/registerUser")
    public JsonResult<Boolean> registerUser(@RequestBody  @Validated  AddUserBO addUserBO) throws IOException {
        return JsonResult.buildSuccess(sysUserService.registerUser(addUserBO));
    }

    @ApiOperation(value = "图形验证码",httpMethod = "GET", produces = "application/json")
    @GetMapping("/generateCaptcha")
    public void generateCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 生成随机验证码文本
        String captchaText = RandomUtil.genRandomNumberStr(6);

        // 使用 CaptchaGenerator 生成验证码图片
        BufferedImage captchaImage = CaptchaGenerator.generateCaptchaImage(captchaText);
        HttpSession session=request.getSession(true);
        session.setAttribute(CoreConstant.VERIFY_CODE,captchaText);
        session.setMaxInactiveInterval(2);
        // 将验证码图片写入响应
        response.setContentType("image/png");
        ImageIO.write(captchaImage, "png", response.getOutputStream());
    }


   // @Log(description = "登录")
    @ApiOperation(value = "登录",httpMethod = "POST", produces = "application/json")
    @PostMapping("/login")
    public JsonResult<Map<String, String>> login(@RequestBody  @Validated LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return JsonResult.buildSuccess(sysUserService.login(loginDTO,request,response));
    }

    @Log(description = "退出登录")
    @ApiOperation(value = "退出登录",httpMethod = "POST", produces = "application/json")
    @PostMapping("/logOut")
    public JsonResult<Boolean> logOut(@ApiParam(name = "userName",value = "用户名",required = true) @RequestParam String userName, HttpServletRequest request, HttpServletResponse response) {
        return JsonResult.buildSuccess(sysUserService.logOut(userName,request,response));
    }

    @ApiOperation("用户导出")
    @PostMapping("/export")
    public JsonResult<String> export(HttpServletResponse response) throws IOException {
        List<UserVO> userVOList = this.sysUserService.exportUser();
        String fileName = URLEncoder.encode("用户信息", String.valueOf(StandardCharsets.UTF_8));
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), UserVO.class)
                .sheet("用户信息")
               // .excludeColumnFiledNames();
                .doWrite(userVOList);
        return JsonResult.buildSuccess();

    }

}

