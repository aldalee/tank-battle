package com.aleecoder;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * 坦克类
 * @author HuanyuLee
 * @date 2022/7/25
 */
public class Tank {
    private int x, y;
    private static final int SPEED = 5;
    private Dir dir;
    private final Group group;
    private boolean bL, bR, bU, bD;
    private boolean isMoving = false;
    private boolean live = true;

    public Tank(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
        if (group == Group.GOOD) {
            switch (dir) {
                case L -> g.drawImage(ResourceMgr.goodTankL, x, y, null);
                case R -> g.drawImage(ResourceMgr.goodTankR, x, y, null);
                case U -> g.drawImage(ResourceMgr.goodTankU, x, y, null);
                case D -> g.drawImage(ResourceMgr.goodTankD, x, y, null);
            }
        }
        if (group == Group.BAD) {
            switch (dir) {
                case L -> g.drawImage(ResourceMgr.badTankL, x, y, null);
                case R -> g.drawImage(ResourceMgr.badTankR, x, y, null);
                case U -> g.drawImage(ResourceMgr.badTankU, x, y, null);
                case D -> g.drawImage(ResourceMgr.badTankD, x, y, null);
            }
        }
        move();
    }

    private void move() {
        if (!isMoving) {
            return;
        }
        switch (dir) {
            case L -> x -= SPEED;
            case R -> x += SPEED;
            case U -> y -= SPEED;
            case D -> y += SPEED;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            // 按下方向键，设置坦克运动的方向
            case KeyEvent.VK_LEFT -> bL = true;
            case KeyEvent.VK_RIGHT -> bR = true;
            case KeyEvent.VK_UP -> bU = true;
            case KeyEvent.VK_DOWN -> bD = true;
            // 按下 CTRL 键，发射子弹
            case KeyEvent.VK_CONTROL -> fire();
        }
        setMainTankDir();
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            // 抬起方向键，坦克停止
            case KeyEvent.VK_LEFT -> bL = false;
            case KeyEvent.VK_RIGHT -> bR = false;
            case KeyEvent.VK_UP -> bU = false;
            case KeyEvent.VK_DOWN -> bD = false;
        }
        setMainTankDir();
    }

    private void fire() {
        // 重新计算子弹发射的位置，在坦克中心打出
        int bx = x + ResourceMgr.TANK_WIDTH / 2 - ResourceMgr.BULLET_WIDTH / 2;
        int by = y + ResourceMgr.TANK_HEIGHT / 2 - ResourceMgr.BULLET_HEIGHT / 2;
        TankFrame.INSTANCE.add(new Bullet(bx, by, dir, group));
    }

    private void setMainTankDir() {
        // 当所有方向键被抬起时，坦克应停止；当任意方向键被按下时，坦克应在相应的方向运动
        isMoving = bL || bR || bU || bD;
        if (bL && !bR && !bU && !bD) {
            dir = Dir.L;
        }
        if (!bL && bR && !bU && !bD) {
            dir = Dir.R;
        }
        if (!bL && !bR && bU && !bD) {
            dir = Dir.U;
        }
        if (!bL && !bR && !bU && bD) {
            dir = Dir.D;
        }
        // 斜着走 类似
    }

    public void die() {
        this.setLive(false);
    }
}
