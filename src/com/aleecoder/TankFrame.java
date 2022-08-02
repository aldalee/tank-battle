package com.aleecoder;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * 坦克大战的窗口程序
 * @author HuanyuLee
 * @date 2022/7/18
 */
public class TankFrame extends Frame {
    private Player tank;
    private List<Tank> enemies;
    private List<Bullet> bullets;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public final int GAME_WIDTH = (int) screenSize.getWidth();
    public final int GAME_HEIGHT = (int) screenSize.getHeight();
    public static final TankFrame INSTANCE = new TankFrame();

    private TankFrame() {
        this.setTitle("tank-single");
        this.setLocation(0, 0);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setVisible(true);
        this.setResizable(false);
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
        initGameObjects();
    }

    private void initGameObjects() {
        tank = new Player(GAME_WIDTH / 2, GAME_HEIGHT - ResourceMgr.TANK_HEIGHT, Dir.U, Group.GOOD);
        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            enemies.add(new Tank(GAME_WIDTH / 3 + ResourceMgr.TANK_WIDTH * i, 30, Dir.D, Group.BAD));
        }
    }

    /**
     * 重写paint方法
     * 系统调用paint方法，并非自己调用
     * @param g 图形中系统传递的画笔
     */
    @Override
    public void paint(Graphics g) {
        initTank(g);
        setCount(g);
        // 子弹生命检查
        bullets.removeIf(bullet -> !bullet.isLive());
        for (Bullet bullet : bullets) {
            for (Tank enemy : enemies) {
                bullet.collideWithTank(enemy);
            }
            bullet.paint(g);
        }

    }

    /**
     * 子弹、敌军坦克计数
     * @param g 系统画笔
     */
    private void setCount(Graphics g) {
        // 保留现场
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
        g.drawString("bullets: " + bullets.size(), 10, 50);
        g.drawString("enemies: " + enemies.size(), 10, 70);
        // 恢复现场
        g.setColor(color);
    }

    /**
     * paint初始化坦克
     * @param g 系统画笔
     */
    public void initTank(Graphics g) {
        tank.paint(g);
        enemies.removeIf(enemy -> !enemy.isLive());
        for (Tank enemy : enemies) {
            enemy.paint(g);
        }
    }

    Image offScreenImage = null;

    /**
     * 双缓冲机制，消除坦克大战中景物的闪烁和白条
     * @param g 系统画笔
     */
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    /**
     * 添加子弹
     * @param bullet 子弹对象
     */
    public void add(Bullet bullet) {
        this.bullets.add(bullet);
    }

    public int getGAME_WIDTH() {
        return GAME_WIDTH;
    }

    public int getGAME_HEIGHT() {
        return GAME_HEIGHT;
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
