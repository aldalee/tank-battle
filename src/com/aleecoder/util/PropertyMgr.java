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
    public static Integer get(String key) {
        return Integer.parseInt((String) PROPS.get(key));
    }
}
