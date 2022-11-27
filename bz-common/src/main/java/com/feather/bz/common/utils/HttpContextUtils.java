package com.feather.bz.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.common.utils
 * @className: HttpContextUtils
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022-11-27 8:49
 * @version: 1.0
 */

public class HttpContextUtils {
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
}
