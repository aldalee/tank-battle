package com.aleecoder.objects;

import com.aleecoder.chain.ColliderChain;
import com.aleecoder.enums.Dir;
import com.aleecoder.enums.Group;
import com.aleecoder.objects.tank.Enemy;
import com.aleecoder.objects.tank.Player;
import com.aleecoder.util.PropertyMgr;
import com.aleecoder.util.ResourceMgr;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理游戏物体
 * @author HuanyuLee
 * @date 2022/8/9
 */
public class GameModel {
    /**
     * 游戏对象
     */
    private Player player;
    private List<AbstractGameObject> objects;
    /**
     * 碰撞规则 责任链模式
     */
    private ColliderChain chain = new ColliderChain();

    public GameModel() {
        initGameObjects();
    }

    /**
     * 初始化游戏对象
     */
    private void initGameObjects() {
        final int TANK_HEIGHT = ResourceMgr.TANK_HEIGHT;
        final int TANK_WIDTH = ResourceMgr.TANK_WIDTH;
        /**
         * 配置文件信息
         */
        final int TANK_COUNT = Integer.parseInt(PropertyMgr.get("initTankCount"));
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final int GAME_WIDTH = (int) screenSize.getWidth();
        final int GAME_HEIGHT = (int) screenSize.getHeight();
        objects = new ArrayList<>();
        // 主战坦克在屏幕最下方中心位置显示
        player = new Player(GAME_WIDTH / 2, GAME_HEIGHT - TANK_HEIGHT, Dir.U, Group.GOOD);
        // 敌军坦克在屏幕最上方中心位置显示一排
        for (int i = 0; i < TANK_COUNT; i++) {
            this.add(new Enemy(GAME_WIDTH / 3 + TANK_WIDTH * i, 30, Dir.D, Group.BAD));
        }
        // 添加一面墙
        this.add(new Wall(300, 200, 200, 100));
    }

    /**
     * 添加游戏物体到屏幕
     * @param gameObject 游戏物体
     */
    public void add(AbstractGameObject object) {
        objects.add(object);
    }
}
