package com.rocketracer.game.ECS.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

public class CollisionComponent implements Component {

    //The component that the rocket has collided with
    public Entity collidedComponent;
}
