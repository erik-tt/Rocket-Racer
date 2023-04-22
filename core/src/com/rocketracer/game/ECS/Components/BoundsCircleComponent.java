package com.rocketracer.game.ECS.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Circle;
import com.rocketracer.game.SharedData.GameConfig;


public class BoundsCircleComponent implements Component  {

    //Creates a new circle
    public Circle bounds = new Circle();


    //Constructor
    public BoundsCircleComponent(int radius ){
        bounds.radius = radius/ GameConfig.PPM;
    }

}
