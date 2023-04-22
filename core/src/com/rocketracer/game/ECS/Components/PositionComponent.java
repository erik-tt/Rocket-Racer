package com.rocketracer.game.ECS.Components;
import com.badlogic.ashley.core.Component;

public class PositionComponent implements Component {
    // Attributes
    public float x;
    public float y;

    // Constructors
    public PositionComponent(float x, float y) {
        this.x = x;
        this.y = y;
    }

    //Updates the x-value
    public void updateX(float x) {
        this.x += x;
    }

}
