package com.feather.bz.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feather.bz.common.core.ObjectMapperImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.common.utils
 * @className: ServletUtil
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022-11-26 21:18
 * @version: 1.0
 */
@Slf4j
public class ServletUtil {
    /**
     * 从应用上下文获取HttpServletRequest组件
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getRequest();
    }

    /**
     * 从应用上下文获取HttpServletResponse组件
     *
     * @return
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getResponse();
    }

    /**
     * 响应json结果
     *
     * @param response
     * @param obj
     */
    public static void writeJsonMessage(HttpServletResponse response, Object obj) {

        ObjectMapper objectMapper = new ObjectMapperImpl();
        response.setContentType("application/json; charset=utf-8");

        try (PrintWriter writer = response.getWriter()) {
            writer.print(objectMapper.writeValueAsString(obj));

            response.flushBuffer();

        } catch (IOException e) {
            log.warn("响应json结果错误", e);
        }
    }
}
