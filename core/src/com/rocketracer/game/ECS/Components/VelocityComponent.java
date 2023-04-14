package com.rocketracer.game.ECS.Components;

import com.badlogic.ashley.core.Component;

public class VelocityComponent implements Component {
    // --- Attributes ---
    public float x;
    public float y;
    public boolean speedSet = false;


    // --- Constructors ---
    public VelocityComponent() {

    }

    public void setSpeed(float x, float y) {
        this.x = x;
        this.y = y;
        speedSet = true;
    }

    //Since speed is only set once.
    public boolean isSpeedSet() {
        return speedSet;
    }
}
