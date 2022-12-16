package com.feather.bz.common.core;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.common.core
 * @className: CloneDirection
 * @author: feather(杜雪松)
 * @description: 克隆方向枚举
 * @since: 2022-12-16 10:09
 * @version: 1.0
 */
public enum CloneDirection {
    /**
     * 正向克隆：从VO->DTO，DTO->DO
     */
    FORWARD(1),
    /**
     * 反向克隆：从DO->DTO，DTO->VO
     */
    OPPOSITE(2);

    private Integer code;

    CloneDirection(Integer code) {
        this.code = code;
    }
}
