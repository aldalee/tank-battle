package com.aleecoder.tank;

import com.aleecoder.Bullet;
import com.aleecoder.TankFrame;
import com.aleecoder.enums.Dir;
import com.aleecoder.enums.Group;
import com.aleecoder.util.ResourceMgr;
import com.aleecoder.view.Audio;
import com.aleecoder.view.Explode;

import java.awt.*;

/**
 * 坦克抽象父类
 * @author HuanyuLee
 * @date 2022/8/2
 */
public abstract class AbstractTank {
    public int x, y;
    public int oldX, oldY;
    public static final int SPEED = 5;
    public static int width = ResourceMgr.TANK_WIDTH;
    public static int height = ResourceMgr.TANK_HEIGHT;
    public Dir dir;
    public Group group;
    public boolean move = true;
    public boolean live = true;

    public AbstractTank(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.oldX = x;
        this.oldY = y;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void tankSpeed(Dir dir,int speed) {
        switch (dir) {
            case L -> x -= speed;
            case R -> x += speed;
            case U -> y -= speed;
            case D -> y += speed;
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

    /**
     * 绘制坦克
     * @param g 系统画笔
     */
    public abstract void paint(Graphics g);

    /**
     * 坦克移动
     */
    public abstract void move();

    /**
     * 坦克边界检查
     */
    public void boundCheck() {
        int screen_width = TankFrame.getInstance().GAME_WIDTH;
        int screen_height = TankFrame.getInstance().GAME_HEIGHT;
        int menu_height = 30;
        if (x < 0 || y < menu_height || x + width > screen_width || y + height > screen_height) {
            this.back();
        }
    }

    private void back() {
        this.x = oldX;
        this.y = oldY;
    }

    public void fire() {
        // 重新计算子弹发射的位置，在坦克中心打出
        int bx = x + width / 2 - ResourceMgr.BULLET_WIDTH / 2;
        int by = y + height / 2 - ResourceMgr.BULLET_HEIGHT / 2;
        TankFrame.getInstance().add(new Bullet(bx, by, dir, group));
        if (this.group == Group.GOOD) {
            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
        }
    }

    public void die() {
        this.setLive(false);
        TankFrame.getInstance().add(new Explode(x, y));
    }
}
