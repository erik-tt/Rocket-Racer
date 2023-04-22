package com.rocketracer.game.ECS.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.rocketracer.game.ECS.Components.CleanupComponent;
import com.rocketracer.game.ECS.Components.CollisionComponent;
import com.rocketracer.game.ECS.Components.PositionComponent;

/**
 * Deals with cleanup of entities in the game
 */

public class CleanupSystem extends IteratingSystem {

    //Attributes
    private ComponentMapper<CleanupComponent> cleanupMapper;
    private ComponentMapper<PositionComponent> positionMapper;
    private ComponentMapper<CollisionComponent> collisionMapper;
    private Engine engine;

    //Constructor
    public CleanupSystem(Engine engine) {
        //Gets entities that has CleanupComponent, PositionComponent and CollisionComponent
        super(Family.all(CleanupComponent.class, PositionComponent.class, CollisionComponent.class).get());

        //Maps the components
        cleanupMapper = ComponentMapper.getFor(CleanupComponent.class);
        positionMapper = ComponentMapper.getFor(PositionComponent.class);
        collisionMapper = ComponentMapper.getFor(CollisionComponent.class);
        this.engine = engine;
    }

    //Method
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
         PositionComponent position = positionMapper.get(entity);
         CollisionComponent collision = collisionMapper.get(entity);

         //Call remove entity when the entity is at the bottom of the screen or has collided
         if(position.y < 0 || collision.hit ) {
             engine.removeEntity(entity);
         }

    }
}
