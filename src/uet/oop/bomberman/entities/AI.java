package com.carlosflorencio.bomberman.entities.mob.enemy.ai;

import java.util.Random;

/**
 * Tạo lớp trừu tượng AI
 */

public abstract class AI {

    protected Random random = new Random();

    public abstract int calculateDirection();
}
