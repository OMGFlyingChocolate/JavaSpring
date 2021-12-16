package com.mybatis.util;

import com.google.gson.Gson;

/**
 * @author zwh
 */
public class JsonUtil {
    /**
     * 功能描述：把JSON数据转换成指定的java对象
     *
     * @param dto   dto对象
     * @param clazz 指定的java对象
     * @return 指定的java对象
     */
    public static <T> T getJsonToBean(Object dto, Class<T> clazz) {
        return new Gson().fromJson(getObjectToString(dto), clazz);
    }

    /**
     * 功能描述：把java对象转换成JSON数据
     *
     * @param object java对象
     * @return JSON数据
     */
    public static String getObjectToString(Object object) {
        return new Gson().toJson(object);
    }
}
