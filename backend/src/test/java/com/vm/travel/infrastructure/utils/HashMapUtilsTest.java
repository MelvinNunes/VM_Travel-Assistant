package com.vm.travel.infrastructure.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HashMapUtilsTest {

    @Test
    @DisplayName("Success Case - Check if can successfully get the first key of a map")
    void get_first_key_from_map() {
        LinkedHashMap<String, Integer> initialMap = new LinkedHashMap<>();
        initialMap.put("firstKey", 1);
        initialMap.put("secondKey", 2);
        initialMap.put("thirdKey", 2);

        assertEquals("firstKey", HashMapUtils.getFirstKeyFromMap(initialMap));
    }

    @Test
    @DisplayName("Fail Case - Check if picking another key, that is not first, passes")
    void get_second_key_from_map() {
        LinkedHashMap<String, Integer> initialMap = new LinkedHashMap<>();
        initialMap.put("secondKey", 1);
        initialMap.put("firstKey", 4);
        initialMap.put("thirdKey", 2);
        String key = HashMapUtils.getFirstKeyFromMap(initialMap);
        assertNotEquals("firstKey", key);
    }

    @Test
    @DisplayName("Check if null is returned for an empty map")
    void get_key_from_empty_map() {
        LinkedHashMap<String, Integer> emptyMap = new LinkedHashMap<>();
        assertNull(HashMapUtils.getFirstKeyFromMap(emptyMap));
    }
}