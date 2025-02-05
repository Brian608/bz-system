package com.feather.bz.manage.domain.enums;

import com.feather.bz.common.enums.BaseEnum;
import com.feather.bz.manage.annoation.ArrayValuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.manage.domain.enums
 * @className: sexEnums
 * @author: feather
 * @description:
 * @since: 2025-02-05 17:41
 * @version: 1.0
 */
@AllArgsConstructor
@Getter
public enum SexEnum implements BaseEnum, ArrayValuable<Integer> {

    MALE(1,"男"),
    FEMALE(0,"女"),
    SECRECY(2,"保密");

    private final Integer code;

    private final String msg;

    public static  final Integer[] ARRAYS= Arrays.stream(values()).map(SexEnum::getCode).toArray(Integer[]::new);

    @Override
    public Integer[] array() {
        return ARRAYS;
    }
}
