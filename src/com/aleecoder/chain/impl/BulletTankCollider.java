package com.aleecoder.chain.impl;

import com.aleecoder.chain.Collider;
import com.aleecoder.objects.AbstractGameObject;
import com.aleecoder.objects.bullet.Bullet;
import com.aleecoder.objects.tank.Tank;

/**
 * 子弹和坦克的碰撞检测
 * @author HuanyuLee
 * @date 2022/8/7
 */
public class BulletTankCollider implements Collider {
    @Override
    public boolean collide(AbstractGameObject o1, AbstractGameObject o2) {
        if (o1 instanceof Bullet bullet && o2 instanceof Tank tank) {
            if (!tank.isLive()){
                return false;
            }
            bullet.collideWithTank(tank);
        } else if (o1 instanceof Tank && o2 instanceof Bullet) {
            return collide(o2, o1);
        }
        return true;
    }
}
