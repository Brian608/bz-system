package com.feather.bz.manage.domain.dto;

import com.feather.bz.common.domain.BasePage;
import lombok.Data;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.manage.domain.query
 * @className: LogQueryDTO
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022-11-27 10:32
 * @version: 1.0
 */
@Data
public class LogQueryDTO  extends BasePage {

    private String user;
}
