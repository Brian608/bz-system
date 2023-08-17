package com.feather.bz.common.enums;

import com.feather.bz.common.exception.BaseErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.common.enums
 * @className: FileErrcorCode
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2023-03-03 17:50
 * @version: 1.0
 */
@Getter
@AllArgsConstructor
public enum FileErrorCodeEnum  implements BaseErrorCodeEnum {
    FILE_IS_NOT_EXIST("400001", "文件不存在")
    ;
    private final String errorCode;

    private final String errorMsg;


}
