package com.rocketracer.game.ECS.Systems;

/**
 * Listens to collision events emitted by the {@link CollisionSystem}
 */

public interface CollisionListener {
    void hitObstacle();

}
