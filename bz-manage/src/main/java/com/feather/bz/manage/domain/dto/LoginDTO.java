package com.feather.bz.manage.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.manage.domain.dto
 * @className: LoginDTO
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022-12-02 11:21
 * @version: 1.0
 */

@Data
public class LoginDTO {

    @NotNull(message = "用户名不能为空")
    private String userName;

    @NotNull(message = "密码不能为空")
    private String password;

    @NotNull(message = "图形验证码不能为空")
    private String verifyCode;
}
