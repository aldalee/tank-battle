package com.aleecoder.tank;

import com.aleecoder.Bullet;
import com.aleecoder.TankFrame;
import com.aleecoder.enums.Dir;
import com.aleecoder.enums.Group;
import com.aleecoder.util.ResourceMgr;
import com.aleecoder.view.Audio;
import com.aleecoder.view.Explode;

import java.awt.*;
import java.util.Random;

/**
 * 坦克抽象父类
 * @author HuanyuLee
 * @date 2022/8/2
 */
public abstract class Tank {
    public int x, y;
    public int oldX, oldY;
    public static final int SPEED = 5;
    public Dir dir;
    public Group group;
    public boolean isMoving = true;
    public boolean live = true;
    public final Random R = new Random();
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

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getOldX() {
        return oldX;
    }

    public void setOldX(int oldX) {
        this.oldX = oldX;
    }

    public int getOldY() {
        return oldY;
    }

    public void setOldY(int oldY) {
        this.oldY = oldY;
    }

    public static int getSPEED() {
        return SPEED;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void tankSpeed(Dir dir) {
        switch (dir) {
            case L -> x -= SPEED;
            case R -> x += SPEED;
            case U -> y -= SPEED;
            case D -> y += SPEED;
        }
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public Random getR() {
        return R;
    }

    public static int getWidth() {
        return width;
    }

    public static void setWidth(int width) {
        Tank.width = width;
    }

    public static int getHeight() {
        return height;
    }

    public static void setHeight(int height) {
        Tank.height = height;
    }

    public abstract void paint(Graphics g);

    public abstract void move();

    /**
     * 坦克边界检查
     */
    public void boundCheck() {
        if (x < 0 || y < 30 || x + width > TankFrame.INSTANCE.getGAME_WIDTH() || y + height > TankFrame.INSTANCE.getGAME_HEIGHT()) {
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
        TankFrame.INSTANCE.add(new Bullet(bx, by, dir, group));
        if(this.group == Group.GOOD) {
            new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
        }
    }


    public void die() {
        this.setLive(false);
        TankFrame.INSTANCE.add(new Explode(x, y));
    }
}
