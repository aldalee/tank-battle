package com.aleecoder;

import java.util.Random;

/**
 * 坦克方向 枚举类
 * 好处，编译期间可以发现问题
 * @author HuanyuLee
 * @date 2022/7/25
 */
public enum Dir {
    // 坦克方向 left right up down
    L, R, U, D;
    private static final Random RANDOM = new Random();

    /**
     * 获取随机方向
     * @return com.aleecoder.Dir
     */
    public static Dir randomDir() {
        return values()[RANDOM.nextInt(values().length)];
    }
}
