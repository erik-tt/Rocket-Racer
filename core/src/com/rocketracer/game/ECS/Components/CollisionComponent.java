package com.rocketracer.game.ECS.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Pool;

public class CollisionComponent implements Component, Pool.Poolable {

    //The component that the rocket has collided with
    public Entity collidedEntity;

    //Says if the component has collided
    public boolean hit;

    // Constructor
    @Override
    public void reset() {
        hit = false;
    }
}
