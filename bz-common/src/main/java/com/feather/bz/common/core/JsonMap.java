package com.feather.bz.common.core;

import java.util.HashMap;
import java.util.Map;

/**
 * @projectName: bz-system
 * @package: com.feather.bz.common.core
 * @className: JsonMap
 * @author: feather(杜雪松)
 * @description: 自定义Map实现，完全兼容java.util.HashMap
 * @since: 2022-11-26 21:17
 * @version: 1.0
 */

public class JsonMap<K, V> extends HashMap<K, V> {

    public JsonMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public JsonMap(int initialCapacity) {
        super(initialCapacity);
    }

    public JsonMap() {
    }

    public JsonMap(Map<? extends K, ? extends V> m) {
        super(m);
    }
}
