package com.aleecoder;

import java.util.concurrent.TimeUnit;

/**
 * 坦克大战主程序入口
 * @author HuanyuLee
 * @date 2022/7/18
 */
public class Main {
    public static void main(String[] args) {
        // 单例模式
        TankFrame tank = TankFrame.INSTANCE;
        // 测试刷新
        for (; ; ) {
            try {
                TimeUnit.MICROSECONDS.sleep(25000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tank.repaint();
        }
    }

}
