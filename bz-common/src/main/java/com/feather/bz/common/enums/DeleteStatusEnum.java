package com.feather.bz.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.common.enums
 * @className: DeleteStatusEnum
 * @author: feather(杜雪松)
 * @description: 数据对象删除状态 0:未删除  1:已删除
 * @since: 2022-11-26 21:09
 * @version: 1.0
 */
@Getter
@AllArgsConstructor
public enum DeleteStatusEnum {
    NO(0, "未删除"),
    YES(1, "已删除");

    private final Integer code;

    private final  String msg;


//    public static Map<Integer, String> toMap(){
//        Map<Integer, String> map = new HashMap<>(16);
//        for (DeleteStatusEnum element : DeleteStatusEnum.values() ){
//            map.put(element.getCode(),element.getMsg());
//        }
//        return map;
//    }

    public static Map<Integer, String> toMap() {
        return Arrays.stream(DeleteStatusEnum.values())
                .collect(Collectors.toMap(DeleteStatusEnum::getCode, DeleteStatusEnum::getMsg));
    }

    public static DeleteStatusEnum of(String code) {

        Objects.requireNonNull(code);

        return Stream.of(values())
                .filter(bean -> bean.code.equals(code))
                .findAny()
                .orElseThrow(
                        () -> new IllegalArgumentException(code + " not exists")
                );
    }
}
