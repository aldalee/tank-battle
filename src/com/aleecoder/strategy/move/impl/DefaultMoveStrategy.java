package com.aleecoder.strategy.move.impl;

import com.aleecoder.strategy.move.MoveStrategy;

/**
 * 坦克默认的移动策略
 * @author HuanyuLee
 * @date 2022/8/6
 */
public class DefaultMoveStrategy implements MoveStrategy {
    // 敌军坦克、我方坦克具备不同的运动策略 由配置文件控制游戏的难度
    // 我方坦克初始时是静止的、敌军坦克初始时是运动的
    // 1、都是默认的移动速度  初级
    // 2、敌军坦克的移动速度是固定的，我方坦克的移动速度是可控的（由按键决定）  中级
    // 3、敌军坦克的移动速度是随机的，我方坦克的移动速度是固定的    中高级
    // 4、敌军坦克的移动速度是随机的，我方坦克的移动速度是可控的    高级

    // 子弹的运动策略
    // 1、匀速运动
    // 2、由快到慢 降到 0 的时候 || 出界，子弹应该移除
    // 3、速度随机
}
