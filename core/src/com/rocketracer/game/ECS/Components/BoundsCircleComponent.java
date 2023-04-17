package com.rocketracer.game.ECS.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Circle;
import com.rocketracer.game.SharedData.GameConfig;


public class BoundsCircleComponent implements Component  {

    public Circle bounds = new Circle();


    public BoundsCircleComponent(int radius ){
        bounds.radius = radius/ GameConfig.PPM;
    }

}
