package com.feather.bz.common.enums;

import com.feather.bz.common.exception.BaseErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.common.enums
 * @className: ErrorCodeEnum
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022-11-26 22:34
 * @version: 1.0
 */
@Getter
@AllArgsConstructor
public enum ErrorCodeEnum  implements BaseErrorCodeEnum {

    REQUEST_IS_NULL("1000001", "客户端请求参数为空");
    private final  String errorCode;

    private final String errorMsg;

}
