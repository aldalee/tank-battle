package com.aleecoder.util;

import java.io.IOException;
import java.util.Properties;

/**
 * 配置文件管理
 * @author HuanyuLee
 * @date 2022/8/5
 */
public class PropertyMgr {
    private static final Properties PROPS;

    static {
        PROPS = new Properties();
        try {
            PROPS.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据key获取配置文件相应的value
     * @param key 配置文件的key
     * @return java.lang.String
     */
    public static String get(String key) {
        return (String) PROPS.get(key);
    }
}
