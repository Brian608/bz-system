package com.feather.bz.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.common.utils
 * @className: BrowserDetectionUtils
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2023-04-07 14:23
 * @version: 1.0
 */

public class BrowserDetectionUtils {
    public static String getBrowserInfo(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        String browserName = "Unknown";
        String browserVersion = "Unknown";
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            browserName = "Internet Explorer";
            String substring = userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
            browserVersion = substring.split(" ")[1];
        } else if (userAgent.contains("Edge")) {
            browserName = "Microsoft Edge";
            browserVersion = userAgent.substring(userAgent.indexOf("Edge")).split(" ")[0].replace("Edge/", "");
        } else if (userAgent.contains("Chrome")) {
            browserName = "Google Chrome";
            browserVersion = userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0].replace("Chrome/", "");
        } else if (userAgent.contains("Firefox")) {
            browserName = "Mozilla Firefox";
            browserVersion = userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0].replace("Firefox/", "");
        } else if (userAgent.contains("Safari")) {
            browserName = "Apple Safari";
            browserVersion = userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0].replace("Safari/", "");
        }
        return browserName + ":" + browserVersion;
    }
        }
