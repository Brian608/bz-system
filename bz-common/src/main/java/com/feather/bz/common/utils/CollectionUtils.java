package com.feather.bz.common.utils;

import com.alibaba.fastjson.JSON;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.common.utils
 * @className: CollectionUtils
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022-12-16 10:16
 * @version: 1.0
 */

public abstract class CollectionUtils {
    public static <T, R> List<R> map(Collection<T> coll, Function<T, R> transformer) {
        List<R> result = new ArrayList<>(coll.size());
        for (T t : coll) {
            result.add(transformer.apply(t));
        }
        return result;
    }

    @SafeVarargs
    public static <T> List<T> copy(Collection<T>... colls) {
        List<T> list = new ArrayList<>();
        for (Collection<T> coll : colls) {
            list.addAll(coll);
        }
        return list;
    }

    @SafeVarargs
    public static <T> List<T> copySort(Comparator<T> comparator, Collection<T>... colls) {
        List<T> list = copy(colls);
        Collections.sort(list, comparator);
        return list;
    }

    public static <T> List<T> copy(List<?> list, Class<T> clazz) {
        String oldOb = JSON.toJSONString(list);
        return JSON.parseArray(oldOb, clazz);
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>(16);
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
