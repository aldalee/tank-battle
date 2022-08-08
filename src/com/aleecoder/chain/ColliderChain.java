package com.aleecoder.chain;

import com.aleecoder.objects.AbstractGameObject;
import com.aleecoder.util.PropertyMgr;

import java.util.ArrayList;
import java.util.List;

/**
 * 碰撞责任链
 * @author HuanyuLee
 * @date 2022/8/8
 */
public class ColliderChain implements Collider {
    public List<Collider> colliders;

    public ColliderChain() {
        initColliders();
    }

    /**
     * 初始化碰撞策略
     */
    private void initColliders() {
        colliders = new ArrayList<>();
        String[] colliderNames = PropertyMgr.get("colliders").split(",");
        try {
            for (String colliderName : colliderNames) {
                Class<?> clazz = Class.forName("com.aleecoder.chain.impl." + colliderName);
                colliders.add((Collider) clazz.getDeclaredConstructor().newInstance());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean collide(AbstractGameObject o1, AbstractGameObject o2) {
        for (Collider collider : colliders) {
            if (!collider.collide(o1, o2)) {
                return false;
            }
        }
        return true;
    }
}
