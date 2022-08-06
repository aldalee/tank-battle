package com.aleecoder.view;

import com.aleecoder.util.ResourceMgr;

import java.awt.*;

/**
 * 爆炸类
 * @author HuanyuLee
 * @date 2022/8/3
 */
public class Explode {
    private final int x;
    private final int y;
    private int width, height;
    private int step = 0;
    private boolean live = true;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = ResourceMgr.explodes[0].getWidth();
        this.height = ResourceMgr.explodes[0].getHeight();
        Audio.explode();
    }

    public boolean isLive() {
        return live;
    }

    public void paint(Graphics g) {
        if (!live){
            return;
        }
        // 一帧一帧的显示爆炸效果
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if (step >= ResourceMgr.explodes.length){
            this.over();
        }
    }

    private void over() {
        live = false;
    }
}
