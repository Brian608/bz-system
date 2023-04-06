package com.feather.bz.manage.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.manage.domain.vo
 * @className: UserVO
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2023-04-06 14:17
 * @version: 1.0
 */
@Data
public class UserVO {
    /**
     * 用户名 用户名
     */
    @ExcelProperty(value = "用户名")
    private String username;



    /**
     * 真实姓名
     */
    @ExcelProperty(value = "真实姓名")
    private String realname;


    /**
     * 手机号 手机号
     */
    @ExcelProperty(value = "手机号")
    private String mobile;

    /**
     * 邮箱地址 邮箱地址
     */
    @ExcelProperty(value = "邮箱地址")
    private String email;

    /**
     * 性别 性别 1:男  0:女  2:保密
     */
    @ExcelProperty(value = "性别")
    private Integer sex;

    /**
     * 生日 生日
     */
    @ExcelProperty(value = "生日")
    private String birthday;
}
