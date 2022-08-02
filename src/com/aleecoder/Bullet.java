package com.aleecoder;

import java.awt.*;

/**
 * 子弹类
 * @author HuanyuLee
 * @date 2022/7/27
 */
public class Bullet {
    private int x, y;
    private Dir dir;
    private final Group group;
    private boolean live = true;
    public static final int SPEED = 5;

    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public void paint(Graphics g) {
        switch (dir) {
            case L -> g.drawImage(ResourceMgr.bulletL, x, y, null);
            case R -> g.drawImage(ResourceMgr.bulletR, x, y, null);
            case U -> g.drawImage(ResourceMgr.bulletU, x, y, null);
            case D -> g.drawImage(ResourceMgr.bulletD, x, y, null);
        }
        move();
    }

    private void move() {
        switch (dir) {
            case L -> x -= SPEED;
            case R -> x += SPEED;
            case U -> y -= SPEED;
            case D -> y += SPEED;
        }
        boundCheck();
    }

    /**
     * 子弹和坦克的碰撞检测
     * @param tank 坦克对象
     */
    public void collideWithTank(Tank tank) {
        // 一颗子弹只能打死一辆坦克
        if (!this.isLive() || !tank.isLive())
            return;
        if (this.group == tank.getGroup())
            return;
        Rectangle rb = new Rectangle(this.x, this.y, ResourceMgr.BULLET_WIDTH, ResourceMgr.BULLET_HEIGHT);
        Rectangle rt = new Rectangle(tank.getX(), tank.getY(), ResourceMgr.TANK_WIDTH, ResourceMgr.TANK_HEIGHT);
        if (rb.intersects(rt)) {
            this.die();
            tank.die();
        }
    }

    /**
     * 子弹边界检查
     */
    private void boundCheck() {
        if (x < 0 || y < 30 || x > TankFrame.INSTANCE.getGAME_WIDTH() || y > TankFrame.INSTANCE.getGAME_HEIGHT()) {
            this.setLive(false);
        }
    }

    public void die() {
        this.setLive(false);
    }
}
