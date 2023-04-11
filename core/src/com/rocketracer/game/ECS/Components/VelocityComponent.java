package com.rocketracer.game.ECS.Components;

import com.badlogic.ashley.core.Component;

public class VelocityComponent implements Component {
    // --- Attributes ---
    public float x;
    public float y;

    // --- Constructors ---
    public VelocityComponent(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
