package com.feather.bz.common.enums;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.common.enums
 * @className: BaseEnum
 * @author: feather
 * @description:
 * @since: 14-Sep-23 4:55 PM
 * @version: 1.0
 */
public interface BaseEnum {

    Integer getCode();

    String getMsg();

    /**
     * code 获取枚举
     * @param code
     * @param clazz
     * @return
     * @param <E>
     */

   static <E extends Enum<E> & BaseEnum> E getByCode(Integer code, Class<E> clazz) {
        Objects.requireNonNull(code);
        EnumSet<E> all = EnumSet.allOf(clazz);
        return all.stream().filter((e) -> ((BaseEnum)e).getCode().equals(code)).findFirst().orElse(null);
    }

    /**
     * 值获取枚举
     * @param msg
     * @param clazz
     * @return
     * @param <E>
     */
    static <E extends Enum<E> & BaseEnum> E getByMsg(String msg, Class<E> clazz) {
        Objects.requireNonNull(msg);
        EnumSet<E> all = EnumSet.allOf(clazz);
        return all.stream().filter((e) -> ((BaseEnum)e).getMsg().equals(msg)).findFirst().orElse(null);
    }

    /**
     * 枚举转 map
     * @param enumClass
     * @return
     * @param <E>
     */
    static <E extends Enum<E> & BaseEnum> Map<Integer, String> toMap(Class<E> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .collect(Collectors.toMap(BaseEnum::getCode, BaseEnum::getMsg));
    }
}
