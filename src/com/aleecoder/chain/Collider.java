package com.aleecoder.chain;

import com.aleecoder.objects.AbstractGameObject;

/**
 * 游戏物体碰撞规则 碰撞器
 * 责任链模式
 * @author HuanyuLee
 * @date 2022/8/7
 */
public interface Collider {
    /**
     * 碰撞检测
     * @param o1 游戏物体对象1
     * @param o2 游戏物体对象2
     * @return boolean true -> chain go on; false -> chain break
     */
    boolean collide(AbstractGameObject o1,AbstractGameObject o2);
}
