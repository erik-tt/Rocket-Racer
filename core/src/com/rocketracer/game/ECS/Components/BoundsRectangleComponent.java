package com.rocketracer.game.ECS.Components;


import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import com.rocketracer.game.SharedData.GameConfig;

public class BoundsRectangleComponent implements Component {

    public Rectangle bounds = new Rectangle();
    public BoundsRectangleComponent(int width, int height ){
        bounds.width = width/ GameConfig.PPM;
        bounds.height = height/ GameConfig.PPM;
    }


}
