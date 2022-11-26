package com.feather.bz.common.exception;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.common.exception
 * @className: BaseErrorCodeEnum
 * @author: feather(杜雪松)
 * @description: 异常错误码枚举抽象定义
 * @since: 2022-11-26 20:53
 * @version: 1.0
 */
public interface BaseErrorCodeEnum {

    String getErrorCode();

    String getErrorMsg();
}
