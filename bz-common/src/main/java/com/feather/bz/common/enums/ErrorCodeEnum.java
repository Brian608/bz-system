package com.feather.bz.common.enums;

import com.feather.bz.common.exception.BaseErrorCodeEnum;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.common.enums
 * @className: ErrorCodeEnum
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022-11-26 22:34
 * @version: 1.0
 */
public enum ErrorCodeEnum  implements BaseErrorCodeEnum {

    REQUEST_IS_NULL("1000001", "客户端请求参数为空");
    private String errorCode;

    private String errorMsg;

    ErrorCodeEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
