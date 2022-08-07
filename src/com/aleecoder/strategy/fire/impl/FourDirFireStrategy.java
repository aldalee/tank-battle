package com.aleecoder.strategy.fire.impl;

import com.aleecoder.view.TankFrame;
import com.aleecoder.enums.Dir;
import com.aleecoder.objects.bullet.Bullet;
import com.aleecoder.objects.tank.Enemy;
import com.aleecoder.objects.tank.Player;
import com.aleecoder.strategy.fire.FireStrategy;
import com.aleecoder.view.Audio;

/**
 * 加强版的开火策略 在四个方向上每次发射一颗子弹
 * @author HuanyuLee
 * @date 2022/8/6
 */
public class FourDirFireStrategy implements FireStrategy {
    @Override
    public void fire(Player tank) {
        int bx = getBx(tank);
        int by = getBy(tank);
        Dir[] dirs = Dir.values();
        for (Dir d : dirs) {
            TankFrame.getInstance().add(new Bullet(bx, by, d, tank.getGroup()));
        }
        Audio.tankFire(tank);
    }

    @Override
    public void fire(Enemy tank) {
        int bx = getBx(tank);
        int by = getBy(tank);
        Dir[] dirs = Dir.values();
        for (Dir d : dirs) {
            TankFrame.getInstance().add(new Bullet(bx, by, d, tank.getGroup()));
        }
    }
}
