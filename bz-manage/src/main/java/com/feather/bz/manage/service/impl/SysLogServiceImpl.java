package com.feather.bz.manage.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feather.bz.common.core.PagingInfo;
import com.feather.bz.manage.domain.SysLog;
import com.feather.bz.manage.domain.dto.LogQueryDTO;
import com.feather.bz.manage.mapper.SysLogMapper;
import com.feather.bz.manage.service.ISysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author feather
 * @since 2022-11-27
 */
@Service
@Slf4j
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

    @Override
    public PagingInfo<SysLog> logPage(LogQueryDTO query) {
        log.info("query={}", JSONObject.toJSONString(query));
        Page<SysLog> page = new Page<>(query.getPageNo(), query.getPageSize());
        Page<SysLog> pageResult = this.page(page);
        return PagingInfo.toResponse(pageResult);
    }
}
