package com.aleecoder.bullet;

import com.aleecoder.TankFrame;
import com.aleecoder.enums.Dir;
import com.aleecoder.enums.Group;
import com.aleecoder.tank.AbstractTank;
import com.aleecoder.util.PropertyMgr;
import com.aleecoder.util.ResourceMgr;

import java.awt.*;

/**
 * 子弹类
 * @author HuanyuLee
 * @date 2022/7/27
 */
public class Bullet {
    private int x, y;
    private final Dir dir;
    private final Group group;
    private boolean live = true;
    private static final int BULLET_SPEED = Integer.parseInt(PropertyMgr.get("BULLET_SPEED"));

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
            case L -> x -= BULLET_SPEED;
            case R -> x += BULLET_SPEED;
            case U -> y -= BULLET_SPEED;
            case D -> y += BULLET_SPEED;
        }
        boundCheck();
    }

    /**
     * 子弹和坦克的碰撞检测
     * @param tank 坦克对象
     */
    public void collideWithTank(AbstractTank tank) {
        // 一颗子弹只能打死一辆坦克
        if (!this.isLive() || !tank.isLive()) {
            return;
        }
        if (this.group == tank.getGroup()) {
            return;
        }
        Rectangle rb = new Rectangle(this.x, this.y, ResourceMgr.BULLET_WIDTH, ResourceMgr.BULLET_HEIGHT);
        Rectangle rt = new Rectangle(tank.x, tank.y, ResourceMgr.TANK_WIDTH, ResourceMgr.TANK_HEIGHT);
        if (rb.intersects(rt)) {
            this.die();
            tank.die();
        }
    }

    /**
     * 子弹边界检查
     */
    private void boundCheck() {
        int screen_width = TankFrame.getInstance().GAME_WIDTH;
        int screen_height = TankFrame.getInstance().GAME_HEIGHT;
        int menu_height = 30;
        if (x < 0 || y < menu_height || x > screen_width || y > screen_height) {
            this.setLive(false);
        }
    }

    public void die() {
        this.setLive(false);
    }
}
