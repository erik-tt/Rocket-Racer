package com.rocketracer.game.ECS.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.rocketracer.game.ECS.Components.CleanupComponent;
import com.rocketracer.game.ECS.Components.CollisionComponent;
import com.rocketracer.game.ECS.Components.FuelComponent;
import com.rocketracer.game.ECS.Components.PositionComponent;

public class CleanupSystem extends IteratingSystem {

    private ComponentMapper<CleanupComponent> cleanupMapper;
    private ComponentMapper<PositionComponent> positionMapper;

    private Engine engine;

    public CleanupSystem(Engine engine) {
        super(Family.all(CleanupComponent.class, PositionComponent.class).get());

        cleanupMapper = ComponentMapper.getFor(CleanupComponent.class);
        positionMapper = ComponentMapper.getFor(PositionComponent.class);
        this.engine = engine;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
         CleanupComponent cleanupObject = cleanupMapper.get(entity);
         PositionComponent position = positionMapper.get(entity);

         if(position.y < 10) {
             engine.removeEntity(entity);
         }

    }
}
