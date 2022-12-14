package com.feather.bz.common.utils;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.feather.bz.common.exception.BaseBizException;
import com.feather.bz.common.exception.BaseErrorCodeEnum;
import com.feather.bz.common.exception.CommonErrorCodeEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.common.utils
 * @className: ParamCheckUtil
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022-11-26 21:29
 * @version: 1.0
 */

public class ParamCheckUtil {
    public static void checkObjectNonNull(Object o) throws BaseBizException {
        if (Objects.isNull(o)) {
            throw new BaseBizException(CommonErrorCodeEnum.SERVER_ILLEGAL_ARGUMENT_ERROR);
        }
    }

    public static void checkObjectNonNull(Object o, BaseErrorCodeEnum baseErrorCodeEnum, Object... arguments) throws BaseBizException {
        if (Objects.isNull(o)) {
            throw new BaseBizException(baseErrorCodeEnum.getErrorCode(), baseErrorCodeEnum.getErrorMsg(),arguments);
        }
    }

    public static void checkObjectNull(Object o, BaseErrorCodeEnum baseErrorCodeEnum, Object... arguments) throws BaseBizException {
        if (Objects.nonNull(o)) {
            throw new BaseBizException(baseErrorCodeEnum.getErrorCode(), baseErrorCodeEnum.getErrorMsg(),arguments);
        }
    }


    public static void checkStringNonEmpty(String s) throws BaseBizException {
        if (StringUtils.isBlank(s)) {
            throw new BaseBizException(CommonErrorCodeEnum.SERVER_ILLEGAL_ARGUMENT_ERROR);
        }
    }

    public static void checkStringNonEmpty(String s, BaseErrorCodeEnum baseErrorCodeEnum, Object... arguments) throws BaseBizException {
        if (StringUtils.isBlank(s)) {
            throw new BaseBizException(baseErrorCodeEnum.getErrorCode(), baseErrorCodeEnum.getErrorMsg(),arguments);
        }
    }

    public static void checkIntAllowableValues(Integer i, Set<Integer> allowableValues, BaseErrorCodeEnum baseErrorCodeEnum, Object... arguments) throws BaseBizException {
        if (Objects.nonNull(i) && !allowableValues.contains(i)) {
            throw new BaseBizException(baseErrorCodeEnum.getErrorCode(), baseErrorCodeEnum.getErrorMsg(),arguments);
        }
    }

    public static void checkIntMin(Integer i, int min, BaseErrorCodeEnum baseErrorCodeEnum,Object... arguments) throws BaseBizException {
        if (Objects.isNull(i) || i<min) {
            throw new BaseBizException(baseErrorCodeEnum.getErrorCode(), baseErrorCodeEnum.getErrorMsg(),arguments);
        }
    }

    public static void checkLongMin(Long i, Long min, BaseErrorCodeEnum baseErrorCodeEnum, Object... arguments) throws BaseBizException {
        if (Objects.isNull(i) || i<min) {
            throw new BaseBizException(baseErrorCodeEnum.getErrorCode(), baseErrorCodeEnum.getErrorMsg(),arguments);
        }
    }

    public static void checkCollectionNonEmpty(Collection<?> collection) throws BaseBizException {
        if (CollectionUtils.isEmpty(collection)) {
            throw new BaseBizException(CommonErrorCodeEnum.SERVER_ILLEGAL_ARGUMENT_ERROR);
        }
    }

    public static void checkCollectionNonEmpty(Collection<?> collection, BaseErrorCodeEnum baseErrorCodeEnum, Object... arguments) throws BaseBizException {
        if (CollectionUtils.isEmpty(collection)) {
            throw new BaseBizException(baseErrorCodeEnum.getErrorCode(), baseErrorCodeEnum.getErrorMsg(),arguments);
        }
    }

    public static void checkIntSetAllowableValues(Set<Integer> intSet, Set<Integer> allowableValues, BaseErrorCodeEnum baseErrorCodeEnum,Object... arguments) throws BaseBizException {
        if (CollectionUtils.isNotEmpty(intSet) && !diffSet(intSet,allowableValues).isEmpty()) {
            throw new BaseBizException(baseErrorCodeEnum.getErrorCode(), baseErrorCodeEnum.getErrorMsg(),arguments);
        }
    }

    public static void checkSetMaxSize(Set<?> setParam, Integer maxSize, BaseErrorCodeEnum baseErrorCodeEnum,Object... arguments) throws BaseBizException {
        if (CollectionUtils.isNotEmpty(setParam) && setParam.size() > maxSize) {
            throw new BaseBizException(baseErrorCodeEnum.getErrorCode(), baseErrorCodeEnum.getErrorMsg(),arguments);
        }
    }

    /**
     * 求set 差集合
     * @param set1
     * @param set2
     * @return
     */
    private static Set<Integer> diffSet(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> result = new HashSet<>();
        result.addAll(set1);
        result.removeAll(set2);
        return result;
    }
}
