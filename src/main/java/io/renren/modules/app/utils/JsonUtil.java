package io.renren.modules.app.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created By Superman
 * Date: 2020/3/15
 * Time: 13:32
 * Description:
 */


public class JsonUtil {
    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}