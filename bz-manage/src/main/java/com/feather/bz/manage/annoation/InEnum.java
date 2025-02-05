package com.feather.bz.manage.annoation;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.manage.annoation
 * @className: InEnum
 * @author: feather
 * @description:
 * @since: 2025-02-05 17:01
 * @version: 1.0
 */

import com.feather.bz.manage.handler.InEnumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({
        ElementType.METHOD,
        ElementType.FIELD,
        ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR,
        ElementType.PARAMETER,
        ElementType.TYPE_USE
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {InEnumValidator.class}
)
public @interface InEnum {
    /**
     * @return 实现 EnumValuable 接口的
     */
    Class<? extends ArrayValuable<?>> value();

    String message() default "必须在指定范围 {value}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
