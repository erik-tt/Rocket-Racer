package com.rocketracer.game.ECS.Systems;

import com.badlogic.ashley.core.Entity;

/**
 * Listens to collision events emitted by the {@link CollisionSystem}
 */

public interface CollisionListener {
    void hitObstacle();

}
