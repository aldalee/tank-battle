package com.aleecoder.tank;

import com.aleecoder.enums.Dir;
import com.aleecoder.enums.Group;
import com.aleecoder.util.ResourceMgr;

import java.awt.*;
import java.util.Random;

/**
 * 敌军坦克类
 * @author HuanyuLee
 * @date 2022/7/25
 */
public class Enemy extends Tank {
    private final Random r = new Random();

    public Enemy(int x, int y, Dir dir, Group group) {
        super(x, y, dir, group);
    }

    @Override
    public void setDir(Dir dir) {
        // 随机数 > 90 时才换方向
        if (r.nextInt(100) > 90) {
            this.dir = dir;
        }
    }

    @Override
    public void paint(Graphics g) {
        if (isLive())
            return;
        switch (dir) {
            case L -> g.drawImage(ResourceMgr.badTankL, x, y, null);
            case R -> g.drawImage(ResourceMgr.badTankR, x, y, null);
            case U -> g.drawImage(ResourceMgr.badTankU, x, y, null);
            case D -> g.drawImage(ResourceMgr.badTankD, x, y, null);
        }
        move();
    }

    @Override
    public void move() {
        if (!isMoving) {
            return;
        }
        oldX = x;
        oldY = y;
        // 设置敌军坦克随机运动的方向
        setDir(Dir.randomDir());
        // 随机数 > 90 才发射子弹
        if (r.nextInt(100) > 90)
            fire();
        tankSpeed(dir);
        boundCheck();
    }
}
