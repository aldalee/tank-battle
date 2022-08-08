package com.aleecoder.chain.impl;

import com.aleecoder.chain.Collider;
import com.aleecoder.objects.AbstractGameObject;
import com.aleecoder.objects.Wall;
import com.aleecoder.objects.bullet.Bullet;

/**
 * 子弹和墙的碰撞检测
 * @author HuanyuLee
 * @date 2022/8/7
 */
public class BulletWallCollider implements Collider {
    @Override
    public boolean collide(AbstractGameObject o1, AbstractGameObject o2) {
        if (o1 instanceof Bullet bullet && o2 instanceof Wall wall) {
            if (!bullet.isLive()) {
                return false;
            }
            if (bullet.getRectangle().intersects(wall.getRectangle())) {
                bullet.die();
                return false;
            }
        } else if (o1 instanceof Wall && o2 instanceof Bullet) {
            return collide(o2, o1);
        }
        return true;
    }
}
