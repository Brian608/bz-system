package com.feather.bz.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.common.utils
 * @className: DataMaskingUtil
 * @author: feather
 * @description: 脱敏
 * @since: 08-Nov-23 4:01 PM
 * @version: 1.0
 */
public class DataMaskingUtil {
    /**
     * 手机号脱敏
     * @param phoneNumber
     * @return
     */
    public static String maskPhoneNumber(String phoneNumber) {
        if (StringUtils.isBlank(phoneNumber)) {
            return phoneNumber;
        }

        // 脱敏手机号中间四位数字
        if (phoneNumber.length() == 11) {
            return phoneNumber.substring(0, 3) + "****" + phoneNumber.substring(7);
        } else {
            // 可能是座机号等其他格式
            return maskString(phoneNumber);
        }
    }

    /**
     * 身份证号脱敏
     * @param idCardNumber
     * @return
     */
    public static String maskIdCardNumber(String idCardNumber) {
        if (StringUtils.isBlank(idCardNumber)) {
            return idCardNumber;
        }

        // 脱敏身份证号的中间数字部分
        int length = idCardNumber.length();
        if (length == 15) {
            return idCardNumber.substring(0, 4) + "********" + idCardNumber.substring(12);
        } else if (length == 18) {
            return idCardNumber.substring(0, 4) + "************" + idCardNumber.substring(14);
        } else {
            return maskString(idCardNumber);
        }
    }

    public static String maskString(String input) {
        if (StringUtils.isBlank(input)) {
            return input;
        }

        int length = input.length();
        int maskLength = Math.max(length / 2, 1);
        char[] maskedChars = new char[length];

        for (int i = 0; i < maskLength; i++) {
            maskedChars[i] = '*';
        }

        for (int i = maskLength; i < length; i++) {
            maskedChars[i] = input.charAt(i);
        }

        return new String(maskedChars);
    }

}
