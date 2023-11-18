package com.feather.bz.manage.handler;

import com.feather.bz.common.enums.BaseEnum;
import com.feather.bz.manage.annoation.EnumValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.manage.handler
 * @className: EnumValueValidator
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022-11-30 21:10
 * @version: 1.0
 */

public class EnumValueValidator  implements ConstraintValidator<EnumValue, Object> {
    private String[] strValues;
    private int[] intValues;

    private Class<? extends BaseEnum> enumClass;

    @Override
    public void initialize(EnumValue constraintAnnotation) {
        strValues = constraintAnnotation.strValues();
        intValues = constraintAnnotation.intValues();
        enumClass = constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value instanceof String) {
            for (String s : strValues) {
                if (s.equals(value)) {
                    return true;
                }
            }
        } else if (value instanceof Integer) {
            for (int s : intValues) {
                if (s == (Integer) value) {
                    return true;
                }
            }
            // Check  enum codes
        }else if (enumClass != null){
            // 使用 enumClass 获取 code 并进行比较
            Integer code = ((BaseEnum) value).getCode();
            return Arrays.stream(intValues).anyMatch(c -> c == code);
        }
        return false;
    }
}
