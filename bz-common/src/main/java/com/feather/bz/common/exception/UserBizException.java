package com.feather.bz.common.exception;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.common.exception
 * @className: MarketBizException
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022-11-30 21:21
 * @version: 1.0
 */

public class UserBizException  extends  BaseBizException{
    public UserBizException(String errorMsg) {
        super(errorMsg);
    }

    public UserBizException(String errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public UserBizException(BaseErrorCodeEnum baseErrorCodeEnum) {
        super(baseErrorCodeEnum);
    }

    public UserBizException(String errorCode, String errorMsg, Object... arguments) {
        super(errorCode, errorMsg, arguments);
    }

    public UserBizException(BaseErrorCodeEnum baseErrorCodeEnum, Object... arguments) {
        super(baseErrorCodeEnum, arguments);
    }
}
