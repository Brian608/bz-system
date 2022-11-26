package com.feather.bz.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.common.enums
 * @className: DeleteStatusEnum
 * @author: feather(杜雪松)
 * @description: 数据对象删除状态 0:未删除  1:已删除
 * @since: 2022-11-26 21:09
 * @version: 1.0
 */
public enum DeleteStatusEnum {
    NO(0, "未删除"),
    YES(1, "已删除");

    private Integer code;

    private String msg;

    DeleteStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static Map<Integer, String> toMap(){
        Map<Integer, String> map = new HashMap<>(16);
        for (DeleteStatusEnum element : DeleteStatusEnum.values() ){
            map.put(element.getCode(),element.getMsg());
        }
        return map;
    }

    public static DeleteStatusEnum getByCode(Integer code){
        for(DeleteStatusEnum element : DeleteStatusEnum.values()){
            if (code.equals(element.getCode())) {
                return element;
            }
        }
        return null;
    }
}
