package com.aleecoder.enums;

import java.util.Random;

/**
 * 坦克方向 枚举类
 * 好处，编译期间可以发现问题
 * @author HuanyuLee
 * @date 2022/7/25
 */
public enum Dir {
    /**
     * 坦克方向 left right up down
     */
    L, R, U, D;
    /**
     * 获取随机方向
     * @return com.aleecoder.enums.Dir
     */
    public static Dir randomDir() {
        return values()[new Random().nextInt(values().length)];
    }
}
