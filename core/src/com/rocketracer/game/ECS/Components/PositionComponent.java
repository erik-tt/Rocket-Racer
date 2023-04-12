package com.rocketracer.game.ECS.Components;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;

public class PositionComponent implements Component {
    // --- Attributes ---
    public float x;
    public float y;
    /** Used for the player entity. If -1, the updateXWith method has never been ran. */

    // --- Constructors ---
    public PositionComponent(float x, float y) {
        this.x = x;
        this.y = y;
        printPosition();
    }
    /**
     * Updates the x position with the given value
     * @param x is the position for the touch
     */
    public void updateXWith(float x, float width) {
        // TODO: Should not directly change x value to the x where the user touches, but move x towards the x where the user touches
        this.x += x;
        printPosition();
    }

    public void printPosition() {
        System.out.println("x: " + x + " y: " + y);
    }
}

