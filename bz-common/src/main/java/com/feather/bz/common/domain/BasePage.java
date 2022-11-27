package com.feather.bz.common.domain;

import lombok.Data;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.common.domain
 * @className: BasePage
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022-11-27 10:30
 * @version: 1.0
 */
@Data
public class BasePage {
    /**
     * 页码
     */
    private Integer pageNo = 1;
    /**
     * 每页条数
     */
    private Integer pageSize = 10;
}
