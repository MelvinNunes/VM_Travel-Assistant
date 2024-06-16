package com.vm.travel.infrastructure.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class HashMapUtils {
    public static <K, V> K getFirstKeyFromMap(LinkedHashMap<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            return entry.getKey();
        }
        return null;
    }
}
