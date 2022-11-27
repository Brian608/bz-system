package com.feather.bz.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feather.bz.common.core.PagingInfo;
import com.feather.bz.manage.domain.SysLog;
import com.feather.bz.manage.domain.dto.LogQueryDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author feather
 * @since 2022-11-27
 */
public interface ISysLogService extends IService<SysLog> {

        PagingInfo<SysLog> logPage(LogQueryDTO query);

}
