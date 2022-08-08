package com.aleecoder.chain.impl;

import com.aleecoder.chain.Collider;
import com.aleecoder.objects.AbstractGameObject;
import com.aleecoder.objects.Wall;
import com.aleecoder.objects.tank.Tank;

/**
 * @author HuanyuLee
 * @date 2022/8/8
 */
public class TankWallCollider implements Collider {
    @Override
    public boolean collide(AbstractGameObject o1, AbstractGameObject o2) {
        if (o1 instanceof Tank tank && o2 instanceof Wall wall) {
            if (!tank.isLive()) {
                return false;
            }
            if (tank.getRectangle().intersects(wall.getRectangle())) {
                tank.back();
            }
        } else if (o1 instanceof Wall && o2 instanceof Tank) {
            collide(o2, o1);
        }
        return true;
    }
}
