package com.aleecoder;

import java.awt.*;
import java.awt.event.*;

/**
 * 坦克大战的窗口程序
 * @author HuanyuLee
 * @date 2022/7/18
 */
public class TankFrame extends Frame {
    private Tank tank;
    private Tank enemy;

    public TankFrame() {
        this.setTitle("tank-single");
        this.setLocation(400, 200);
        this.setSize(800, 600);
        this.setVisible(true);
        // 关闭窗口
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        // TODO: 观察者 设计模式 Observer
        // 手动控制：响应键盘事件
        this.addKeyListener(new TankKeyListener());
        tank = new Tank(100, 100, Direction.RIGHT);
        enemy = new Tank(200, 200, Direction.DOWN);
    }

    /**
     * 重写paint方法
     * 系统调用paint方法，并非自己调用
     * @param g 图形中系统传递的画笔
     */
    @Override
    public void paint(Graphics g) {
        tank.paint(g);
        enemy.paint(g);
    }

    /**
     * 为什么使用内部类？
     * 不需要让别的类访问键盘监听类
     * TODO: 高内聚、低耦合的程序设计原则
     */
    private class TankKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            tank.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            tank.keyReleased(e);
        }
    }
}
