package com.aleecoder.strategy.fire.impl;

import com.aleecoder.view.TankFrame;
import com.aleecoder.objects.bullet.Bullet;
import com.aleecoder.objects.tank.Enemy;
import com.aleecoder.objects.tank.Player;
import com.aleecoder.strategy.fire.FireStrategy;
import com.aleecoder.view.Audio;

/**
 * 默认的开火策略 每次只发射一颗子弹
 * @author HuanyuLee
 * @date 2022/8/6
 */
public class DefaultFireStrategy implements FireStrategy {
    @Override
    public void fire(Player tank) {
        int bx = getBx(tank);
        int by = getBy(tank);
        TankFrame.getInstance().add(new Bullet(bx, by, tank.getDir(), tank.getGroup()));
        Audio.tankFire(tank);
    }

    @Override
    public void fire(Enemy tank) {
        int bx = getBx(tank);
        int by = getBy(tank);
        TankFrame.getInstance().add(new Bullet(bx, by, tank.getDir(), tank.getGroup()));
    }
}
