package com.rocketracer.game.ECS.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Pool;

public class CollisionComponent implements Component, Pool.Poolable {

    //The component that the rocket has collided with
    public Entity collidedComponent;
    public boolean hit;

    @Override
    public void reset() {
        hit = false;
    }
}
