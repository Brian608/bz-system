package com.feather.bz.manage.handler;

import com.feather.bz.manage.annoation.EnumValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

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

    @Override
    public void initialize(EnumValue constraintAnnotation) {
        strValues = constraintAnnotation.strValues();
        intValues = constraintAnnotation.intValues();
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
        }
        return false;
    }
}
