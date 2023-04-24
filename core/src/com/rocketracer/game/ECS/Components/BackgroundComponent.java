package com.rocketracer.game.ECS.Components;

import com.badlogic.ashley.core.Component;

public class BackgroundComponent implements Component {

    // Attributes
    public float y1,y2;
    public int speed;

    // Constructor
    public BackgroundComponent(float y1,float y2, int speed) {
        this.y1 = y1;
        this.y2 = y2;
        this.speed = speed;

    }
}
