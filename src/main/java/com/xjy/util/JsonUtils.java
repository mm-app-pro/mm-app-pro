package com.xjy.util;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public final class JsonUtils {

    /**
     * object to json，默认关闭循环引用，不然无法解析同一个list中的同一个引用对象
     *
     */
    public static String toJsonString(Object object) {
        if (null == object){
            return null;
        }
        return JSON.toJSONString(object, SerializerFeature.DisableCircularReferenceDetect);
    }

    /**
     * object to json use single quotes
     *
     */
    public static String toJsonStringUseSingleQuotes(Object object) {
        return JSON.toJSONString(object, SerializerFeature.UseSingleQuotes);
    }

    /**
     * date to json, yyyy-MM-dd HH:mm:ss
     *
     */
    public static String toJsonStringForDate(Date date) {
        return JSON.toJSONString(date, SerializerFeature.WriteDateUseDateFormat);
    }

    /**
     * json to object
     *
     */
    public static Object parseJson(String json) {
        return JSON.parse(json);
    }

    /**
     * json to map
     *
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> parseJsonToMap(String json) {
        return (Map<String, Object>) JSON.parse(json);
    }

    /**
     * json to T type
     *
     */
    public static <T> T parseJson(String json, Class<T> clazz) {
        if (StringUtils.isBlank(json))
            return null;
        return JSON.parseObject(json, clazz);
    }

    /**
     * json to T type
     *
     */
    public static <T> T parseJson(byte[] bytes, Class<T> clazz) {
        if (bytes.length == 0)
            return null;
        return JSON.parseObject(bytes, clazz);
    }

    /**
     * json to list
     *
     */
    public static <T> List<T> parseJsonToList(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }

    public static boolean isJsonArray(String json) {
        return json != null && json.trim().startsWith("[");
    }

    public static boolean isJsonObject(String json) {
        return json != null && json.trim().startsWith("{");
    }

}
