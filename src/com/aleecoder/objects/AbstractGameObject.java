package com.aleecoder.objects;

import java.awt.*;

/**
 * 游戏物体
 * @author HuanyuLee
 * @date 2022/8/6
 */
public abstract class AbstractGameObject {
    /**
     * 绘制游戏物体
     * @param g 系统画笔
     */
    public abstract void paint(Graphics g);

    /**
     * 游戏物体是否还存活
     * @return boolean
     */
    public abstract boolean isLive();
}
