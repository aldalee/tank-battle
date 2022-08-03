package com.aleecoder;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * 主战坦克类
 * @author HuanyuLee
 * @date 2022/7/31
 */
public class Player extends Tank {
    public boolean isMoving = false;
    private boolean bL, bR, bU, bD;

    public Player(int x, int y, Dir dir, Group group) {
        super(x, y, dir, group);
    }

    @Override
    public void paint(Graphics g) {
        if (!isLive())
            return;
        switch (dir) {
            case L -> g.drawImage(ResourceMgr.goodTankL, x, y, null);
            case R -> g.drawImage(ResourceMgr.goodTankR, x, y, null);
            case U -> g.drawImage(ResourceMgr.goodTankU, x, y, null);
            case D -> g.drawImage(ResourceMgr.goodTankD, x, y, null);
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
        tankSpeed(dir);
        boundCheck();
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
}
