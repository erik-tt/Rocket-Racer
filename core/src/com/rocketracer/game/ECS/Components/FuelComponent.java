package com.rocketracer.game.ECS.Components;
import com.badlogic.ashley.core.Component;

public class FuelComponent implements Component {
    // --- Attributes ---
    public float fuelLevel;
    public float maxLevel;

    // --- Constructor ---
    public FuelComponent(float fuelLevel) {

        this.fuelLevel = fuelLevel;
        System.out.println("Fuellevel" + fuelLevel);
    }


}
