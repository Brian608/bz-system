package com.feather.bz.common.enums;

import com.feather.bz.common.exception.BaseErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.common.enums
 * @className: UserErrorCodeEnum
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022-11-30 21:22
 * @version: 1.0
 */
@Getter
@AllArgsConstructor
public enum UserErrorCodeEnum  implements BaseErrorCodeEnum {
    USER_COUPON_IS_NULL("300001", "密码跟确认密码不一致"),

    USER_EXIST("300002","用户已经存在"),

    USER_NOT_EXIST("300003","用户不存在"),

    LOGINOUT_ERROR("300004","退出失败"),

    PASSWORD_ERROR("300004","密码错误"),

    TOKEN_EXPIRE_ERROR("300005","无效token，请重新登录"),

    ILLEGAL_USER("300006","非法用户"),

    VERIFY_CODE_ERROR("300007","图形验证码错误或过期"),

    ;
    private final String errorCode;

    private final String errorMsg;

}
