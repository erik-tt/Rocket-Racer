package com.rocketracer.game.ECS.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.rocketracer.game.ECS.Components.BackgroundComponent;
import com.rocketracer.game.ECS.Components.PositionComponent;


/**
 * Deals with the background in the game
 */


public class BackgroundSystem extends IteratingSystem {

    //Attributes
    private ComponentMapper<BackgroundComponent> bMapper;
    private ComponentMapper<PositionComponent> pMapper;

    //Constructor
    public BackgroundSystem() {
        super(Family.all(BackgroundComponent.class).get());
        bMapper = ComponentMapper.getFor(BackgroundComponent.class);
        pMapper = ComponentMapper.getFor(PositionComponent.class);
    }

    // Method
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BackgroundComponent background = bMapper.get(entity);
        PositionComponent position = pMapper.get(entity);
        position.y -= background.speed * deltaTime;

        //End of background-image
        if(position.y < -415){
            background.speed=0;
        }
    }
}
