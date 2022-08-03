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
    public Player player;
    public List<Enemy> enemies;
    private List<Bullet> bullets;
    private List<Explode> explodes;
    Image offScreenImage = null;

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

    /**
     * 重写paint方法
     * 系统调用paint方法，并非自己调用
     * @param g 图形中系统传递的画笔
     */
    @Override
    public void paint(Graphics g) {
        initTank(g);
        setCount(g);
        paintBullets(g);
        paintExplodes(g);
    }

    /**
     * 初始化游戏对象
     */
    private void initGameObjects() {
        player = new Player(GAME_WIDTH / 2, GAME_HEIGHT - ResourceMgr.TANK_HEIGHT, Dir.U, Group.GOOD);
        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        explodes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            enemies.add(new Enemy(GAME_WIDTH / 3 + ResourceMgr.TANK_WIDTH * i, 30, Dir.D, Group.BAD));
        }
    }

    /**
     * paint初始化坦克
     * @param g 系统画笔
     */
    public void initTank(Graphics g) {
        player.paint(g);
        enemies.removeIf(enemy -> !enemy.isLive());
        for (Enemy enemy : enemies) {
            enemy.paint(g);
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

    private void paintBullets(Graphics g) {
        // 子弹生命检查
        bullets.removeIf(bullet -> !bullet.isLive());
        for (Bullet bullet : bullets) {
            for (Enemy enemy : enemies) {
                bullet.collideWithTank(enemy);
            }
            // 主战坦克 子弹碰撞
            bullet.collideWithTank(player);
            bullet.paint(g);
        }
    }

    private void paintExplodes(Graphics g) {
        explodes.removeIf(explode -> !explode.isLive());
        for (Explode explode : explodes) {
            explode.paint(g);
        }
    }

    /**
     * 添加子弹
     * @param bullet 子弹对象
     */
    public void add(Bullet bullet) {
        this.bullets.add(bullet);
    }

    /**
     * 添加爆炸
     * @param explode 爆炸对象
     */
    public void add(Explode explode) {
        this.explodes.add(explode);
    }

    public int getGAME_WIDTH() {
        return GAME_WIDTH;
    }

    public int getGAME_HEIGHT() {
        return GAME_HEIGHT;
    }

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
     * 为什么使用内部类？
     * 不需要让别的类访问键盘监听类
     * TODO: 高内聚、低耦合的程序设计原则
     */
    private class TankKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }
    }
}
