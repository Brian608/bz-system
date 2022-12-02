package com.feather.bz.common.constants;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.common.constants
 * @className: RedisConstants
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022-12-02 11:17
 * @version: 1.0
 */
public interface RedisConstants {

    /**
     * token 私钥
     */
    String TOKEN_SECRET="123456";

    /**
     * 过期时长
     */
    Long DURATION = 1 * 24 * 60 * 60 * 1000L;

    String USER="USER:";

    String SPEC="_";
}
