package com.feather.bz.manage.domain.bo;

import com.feather.bz.manage.annoation.EnumValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.manage.domain.bo
 * @className: AddUserBO
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022-11-30 20:57
 * @version: 1.0
 */
@Data
@ApiModel(value = "用户对象BO",description = "从客户端，由用户传入的数据封装的BO")
public class AddUserBO {

    @Length(max = 50,message = "姓名最长50")
    @Pattern(regexp = "^[a-zA-Z0-9]+$",message = "用户名只能包含数字和字母，并且不能超过50个字符")
    @NotNull(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名",name ="username",example = "admin",required = true)
    private String username;


    @NotNull(message = "密码不能为空")
    @ApiModelProperty(value = "密码 md5后再base64",name ="password",required = true)
    private String password;

//    @NotNull(message = "确认密码不能为空")
//    @ApiModelProperty(value = "确认密码",name ="confirmPassWord",example = "123456",required = true)
//    private String confirmPassWord;

    /**
     * 真实姓名
     */
    @Length(max = 50,message = "真实姓名最长50")
    @NotNull(message = "真实姓名不能为空")
    @ApiModelProperty(value = "真实姓名",name ="realname",example = "管理员",required = true)
    private String realname;


    /**
     * 手机号 手机号
     */
    @NotNull(message = "手机号不能为空")
    @Length(max = 11)
    @Pattern(regexp = "1[3|4|5|7|8][0-9]{9}$",message = "手机号格式错误")
    @ApiModelProperty(value = "手机号",name ="mobile",example = "18888888888",required = true)
    private String mobile;

    /**
     * 邮箱地址 邮箱地址
     */
    @NotNull(message = "邮箱不能为空")
    @Length(max = 50)
    @Pattern(regexp = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$",message = "邮箱格式错误")
    @ApiModelProperty(value = "邮箱",name ="email",example = "123@qq.com",required = true)
    private String email;

    /**
     * 性别 性别 1:男  0:女  2:保密
     */
    @EnumValue(intValues = {0,1,2}, message = "性别值只能为0或1或2")
    @ApiModelProperty(value = "性别",name ="sex",example = "2",required = true)
    @NotNull(message = "性别不能为空")
    private Integer sex;

    /**
     * 生日 生日
     */
    @ApiModelProperty(value = "生日",name ="birthday",example = "xxx",required = false)
    private Date birthday;
}
