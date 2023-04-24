package com.rocketracer.game.ECS.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;
import com.rocketracer.game.ECS.Components.BoundsCircleComponent;
import com.rocketracer.game.ECS.Components.BoundsRectangleComponent;
import com.rocketracer.game.ECS.Components.CollisionComponent;
import com.rocketracer.game.ECS.Components.FuelComponent;
import com.rocketracer.game.ECS.Components.PositionComponent;
import com.rocketracer.game.ECS.Components.SpecificTypeComponent;
import com.rocketracer.game.ECS.Components.SpriteComponent;
import com.rocketracer.game.SharedData.GameConfig;


/**
 * Deals with the collisions in the game.
 */

public class CollisionSystem extends EntitySystem {

    //Attributes

    //Sets family for player
    private static final Family PLAYER_FAMILY = Family.all(
            FuelComponent.class).get();

    //Sets family for the obstacles
    private static final Family OBSTACLE_FAMILY = Family.all(
            SpecificTypeComponent.class,
            CollisionComponent.class).get();

    private final CollisionListener listener;

    //Constructor
    public CollisionSystem(CollisionListener listener) {
        this.listener = listener;
    }

    //Methods
    @Override
    public void update(float deltaTime) {

        //Only one player right now
        ImmutableArray<Entity> players = getEngine().getEntitiesFor(PLAYER_FAMILY);
        ImmutableArray<Entity> obstacles = getEngine().getEntitiesFor(OBSTACLE_FAMILY);

        for(Entity playerEntity: players) {
            for(Entity obstacleEntity: obstacles) {

                CollisionComponent collisionComponent = ComponentMapper.getFor(CollisionComponent.class).get(obstacleEntity);

                //Sets the bounds
                setBounds(playerEntity);
                setBounds(obstacleEntity);

                if (collisionComponent.hit) {
                    continue;
                }

                //Checks if collision has happened
                if (checkCollision(playerEntity, obstacleEntity)) {
                    collisionComponent.hit = true;
                    System.out.println("Collision detected!");
                    FuelComponent fuelComponent = ComponentMapper.getFor(FuelComponent.class).get(playerEntity);
                    SpecificTypeComponent obstacleType = ComponentMapper.getFor(SpecificTypeComponent.class).get(obstacleEntity);

                    //Reduce the players fuel level according to which obstacle-type it has collided with
                    if(obstacleType == SpecificTypeComponent.BIRD){
                        fuelComponent.fuelLevel -= 5;
                    }

                    if(obstacleType == SpecificTypeComponent.PLANE){
                        fuelComponent.fuelLevel -= 10;
                    }

                    if(obstacleType == SpecificTypeComponent.SATELLITE){
                        fuelComponent.fuelLevel -= 15;

                    }
                    if(obstacleType == SpecificTypeComponent.ASTEROID){
                        fuelComponent.fuelLevel -= 20;

                    }

                    //Collects a fuel can
                    if(obstacleType == SpecificTypeComponent.FUELCAN && fuelComponent.fuelLevel < fuelComponent.maxFuelLevel){
                        if(fuelComponent.fuelLevel == 95){
                            fuelComponent.fuelLevel = fuelComponent.maxFuelLevel;
                        }
                        else {
                            fuelComponent.fuelLevel += 10;
                        }
                    }

                    listener.hitObstacle();
                }
            }
        }
    }

    //Checks collision between two entities
    private boolean checkCollision(Entity playerEntity, Entity obstacleEntity) {
        BoundsRectangleComponent playerBounds = ComponentMapper.getFor(BoundsRectangleComponent.class).get(playerEntity);
        BoundsCircleComponent obstacleCircleBounds = ComponentMapper.getFor(BoundsCircleComponent.class).get(obstacleEntity);
        BoundsRectangleComponent obstacleRectangleComponent = ComponentMapper.getFor(BoundsRectangleComponent.class).get(obstacleEntity);

        //Checks intersecting between circle and rectangle
        if(obstacleCircleBounds != null){
        return Intersector.overlaps( obstacleCircleBounds.bounds, playerBounds.bounds);}

        //Checks intersecting between circle and rectangle
        if(obstacleRectangleComponent != null){
            return Intersector.overlaps(playerBounds.bounds, obstacleRectangleComponent.bounds);}

        //No collision
        else{
            return false;
        }
    }

    //Sets bounds to entity
    private void setBounds(Entity entity){
        BoundsCircleComponent boundsCircleComponent = ComponentMapper.getFor(BoundsCircleComponent.class).get(entity);
        BoundsRectangleComponent boundsRectangleComponent = ComponentMapper.getFor(BoundsRectangleComponent.class).get(entity);
        PositionComponent positionComponent = ComponentMapper.getFor(PositionComponent.class).get(entity);
        SpriteComponent spriteComponent = ComponentMapper.getFor(SpriteComponent.class).get(entity);
        SpecificTypeComponent typeComponent =ComponentMapper.getFor(SpecificTypeComponent.class).get(entity);

        //Entity's bounds is a circle component
        if(boundsCircleComponent != null){
            boundsCircleComponent.bounds.x = positionComponent.x + spriteComponent.sprite.getWidth()/(GameConfig.PPM*2);
            boundsCircleComponent.bounds.y = positionComponent.y;
        }

        //Entity's bounds is a rectangle component
        if(boundsRectangleComponent != null){
            if(typeComponent == SpecificTypeComponent.PLANE) {
                boundsRectangleComponent.bounds.setPosition(positionComponent.x , positionComponent.y);

            }
            else {
                boundsRectangleComponent.bounds.setPosition(positionComponent.x + spriteComponent.sprite.getWidth() / (GameConfig.PPM * 2), positionComponent.y);
            }}
    }
}