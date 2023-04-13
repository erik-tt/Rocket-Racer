package com.rocketracer.game.ECS.Entities;

import com.badlogic.ashley.core.Entity;

/**
 * This is used for the factor pattern
 */
public interface IGameObject {

    //Method for creating an entity.
    void build();

    Entity getEntity();
}
