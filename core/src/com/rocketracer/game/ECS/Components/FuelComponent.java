package com.rocketracer.game.ECS.Components;
import com.badlogic.ashley.core.Component;

public class FuelComponent implements Component {
    // --- Attributes ---
    public int fuelLevel;

    // --- Constructor ---
    public FuelComponent(int fuelLevel) {

        this.fuelLevel = fuelLevel;


    }


}
