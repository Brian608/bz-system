package com.feather.bz.common.enums;

import com.feather.bz.common.exception.BaseErrorCodeEnum;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.common.enums
 * @className: FileErrcorCode
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2023-03-03 17:50
 * @version: 1.0
 */
public enum FileErrorCodeEnum  implements BaseErrorCodeEnum {
    FILE_IS_NOT_EXIST("400001", "文件不存在")
    ;
    private String errorCode;

    private String errorMsg;

    FileErrorCodeEnum(String errorCode, String errorMsg) {
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
