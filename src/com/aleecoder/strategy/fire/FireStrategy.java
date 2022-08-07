package com.aleecoder.strategy.fire;

import com.aleecoder.objects.tank.Enemy;
import com.aleecoder.objects.tank.Player;
import com.aleecoder.util.ResourceMgr;

/**
 * 子弹开火策略模式
 * @author HuanyuLee
 * @date 2022/8/6
 */
public interface FireStrategy {
    // TODO: 如何动态的按键调整？而不是根据配置文件
    /**
     * 坦克开火
     * @param tank 坦克对象
     */
    void fire(Player tank);
    void fire(Enemy tank);

    /**
     * 获取子弹的 x 坐标
     * @param tank 坦克对象
     * @return int
     */
    default int getBx(Player tank) {
        return tank.getX() + tank.width / 2 - ResourceMgr.BULLET_WIDTH / 2;
    }
    default int getBx(Enemy tank) {
        return tank.getX() + tank.width / 2 - ResourceMgr.BULLET_WIDTH / 2;
    }

    /**
     * 获取子弹的 y 坐标
     * @param tank 坦克对象
     * @return int
     */
    default int getBy(Player tank) {
        return tank.getY() + tank.height / 2 - ResourceMgr.BULLET_HEIGHT / 2;
    }
    default int getBy(Enemy tank) {
        return tank.getY() + tank.height / 2 - ResourceMgr.BULLET_HEIGHT / 2;
    }
}
