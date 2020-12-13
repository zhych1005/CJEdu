package io.renren.modules.app.utils;

import java.util.HashMap;
import java.util.Map;

public class MapUtils {
    public static Map<String, String> mapStringToMap(String str) {
        str = str.replace(" ", "");
        str = str.substring(1, str.length() - 1);
        String[] s = str.split(",");
        Map<String, String> map = new HashMap<>();
        for (String string : s) {
            String key = string.split("=")[0];
            String value = string.split("=")[1];
            map.put(key, value);
        }
        return map;
    }
}