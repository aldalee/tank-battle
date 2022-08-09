package com.aleecoder.chain.impl;

import com.aleecoder.chain.Collider;
import com.aleecoder.objects.AbstractGameObject;
import com.aleecoder.objects.tank.Tank;

/**
 * 坦克和坦克碰撞的逻辑
 * @author HuanyuLee
 * @date 2022/8/9
 */
public class TankTankCollider implements Collider {
    @Override
    public boolean collide(AbstractGameObject o1, AbstractGameObject o2) {
        // TODO: 主战坦克不能与敌军坦克相撞 掉头
        if (o1 != o2 && o1 instanceof Tank tank1 && o2 instanceof Tank tank2){
            if (!tank1.isLive() || !tank2.isLive()){
                return false;
            }
            if (tank1.getRectangle().intersects(tank2.getRectangle())) {
                tank1.back();
                tank2.back();
            }
        }
        return true;
    }
}
