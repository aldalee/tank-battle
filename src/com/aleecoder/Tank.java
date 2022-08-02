package com.aleecoder;

import java.awt.*;
import java.util.Random;

/**
 * 敌军坦克类
 * @author HuanyuLee
 * @date 2022/7/25
 */
public class Tank {
    private int x, y;
    private int oldX, oldY;
    private static final int SPEED = 5;
    private Dir dir;
    private final Group group;
    private boolean isMoving = true;
    private boolean live = true;
    private final Random r = new Random();
    public static int width = ResourceMgr.TANK_WIDTH;
    public static int height = ResourceMgr.TANK_HEIGHT;

    public Tank(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.oldX = x;
        this.oldY = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setDir(Dir dir) {
        // 随机数 > 90 时才换方向
        if (r.nextInt(100) > 90) {
            this.dir = dir;
        }
    }

    public Group getGroup() {
        return group;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public void paint(Graphics g) {
        if (!isLive())
            return;
        switch (dir) {
            case L -> g.drawImage(ResourceMgr.badTankL, x, y, null);
            case R -> g.drawImage(ResourceMgr.badTankR, x, y, null);
            case U -> g.drawImage(ResourceMgr.badTankU, x, y, null);
            case D -> g.drawImage(ResourceMgr.badTankD, x, y, null);
        }
        move();
    }

    private void move() {
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
        switch (dir) {
            case L -> x -= SPEED;
            case R -> x += SPEED;
            case U -> y -= SPEED;
            case D -> y += SPEED;
        }
        boundCheck();
    }

    /**
     * 坦克边界检查
     */
    private void boundCheck() {
        if (x < 0 || y < 30 || x + width > TankFrame.INSTANCE.getGAME_WIDTH() || y + height > TankFrame.INSTANCE.getGAME_HEIGHT()) {
            this.back();
        }
    }

    private void back() {
        this.x = oldX;
        this.y = oldY;
    }

    private void fire() {
        // 重新计算子弹发射的位置，在坦克中心打出
        int bx = x + width / 2 - ResourceMgr.BULLET_WIDTH / 2;
        int by = y + height/ 2 - ResourceMgr.BULLET_HEIGHT / 2;
        TankFrame.INSTANCE.add(new Bullet(bx, by, dir, group));
    }

    public void die() {
        this.setLive(false);
    }
}
