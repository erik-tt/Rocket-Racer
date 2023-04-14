package com.rocketracer.game.ECS.Components;

import com.badlogic.ashley.core.Component;

public enum ObstacleTypeComponent implements Component {

    /**
     * Used for identifying different types of abstacles.
     */

    BIRD,
    PLANE,
    SATELLITE,
    ASTEROID
}
