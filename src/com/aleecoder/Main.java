package com.aleecoder;

import com.aleecoder.view.Audio;

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
        // bgm
        new Thread(()->new Audio("audio/bgm.wav").loop()).start();
        // 测试刷新
        for (;;){
            try {
                TimeUnit.MICROSECONDS.sleep(25000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tank.repaint();
            // TODO: 如何在游戏结束时在屏幕显示相应的文字 赢或输 再重新开始？
        }
    }

}
