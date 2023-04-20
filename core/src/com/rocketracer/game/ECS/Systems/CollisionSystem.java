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


    /*
     * We need to families here which will contain the entities that can collide
     */
    private static final Family PLAYER_FAMILY = Family.all(
            FuelComponent.class).get();

    private static final Family OBSTACLE_FAMILY = Family.all(
            SpecificTypeComponent.class,
            CollisionComponent.class).get();


    private final CollisionListener listener;

    public CollisionSystem(CollisionListener listener) {
        this.listener = listener;
    }

    @Override
    public void update(float deltaTime) {

        // we only have one player, tho
        ImmutableArray<Entity> players = getEngine().getEntitiesFor(PLAYER_FAMILY);
        ImmutableArray<Entity> obstacles = getEngine().getEntitiesFor(OBSTACLE_FAMILY);
        for(Entity playerEntity: players) {
            for(Entity obstacleEntity: obstacles) {


                CollisionComponent collisionComponent = ComponentMapper.getFor(CollisionComponent.class).get(obstacleEntity);
                setBounds(playerEntity);
                setBounds(obstacleEntity);

                if (collisionComponent.hit) {
                    continue;
                }


                if (checkCollision(playerEntity, obstacleEntity)) {
                    collisionComponent.hit = true;
                    System.out.println("Collision detected!");
                    FuelComponent fuelComponent = ComponentMapper.getFor(FuelComponent.class).get(playerEntity);
                    SpecificTypeComponent obstacleType = ComponentMapper.getFor(SpecificTypeComponent.class).get(obstacleEntity);
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

    private boolean checkCollision(Entity playerEntity, Entity obstacleEntity) {
        BoundsRectangleComponent playerBounds = ComponentMapper.getFor(BoundsRectangleComponent.class).get(playerEntity);
        BoundsCircleComponent obstacleCircleBounds = ComponentMapper.getFor(BoundsCircleComponent.class).get(obstacleEntity);
        BoundsRectangleComponent obstacleRectangleComponent = ComponentMapper.getFor(BoundsRectangleComponent.class).get(obstacleEntity);
        if(obstacleCircleBounds != null){
        return Intersector.overlaps( obstacleCircleBounds.bounds, playerBounds.bounds);}

        if(obstacleRectangleComponent != null){
            return Intersector.overlaps(playerBounds.bounds, obstacleRectangleComponent.bounds);
        }
        else{
            return false;
        }
    }

    private void setBounds(Entity entity){
        BoundsCircleComponent boundsCircleComponent = ComponentMapper.getFor(BoundsCircleComponent.class).get(entity);
        BoundsRectangleComponent boundsRectangleComponent = ComponentMapper.getFor(BoundsRectangleComponent.class).get(entity);
        PositionComponent positionComponent = ComponentMapper.getFor(PositionComponent.class).get(entity);
        SpriteComponent spriteComponent = ComponentMapper.getFor(SpriteComponent.class).get(entity);
        SpecificTypeComponent typeComponent =ComponentMapper.getFor(SpecificTypeComponent.class).get(entity);

        if(boundsCircleComponent != null){
            boundsCircleComponent.bounds.x = positionComponent.x + spriteComponent.sprite.getWidth()/(GameConfig.PPM*2);
            boundsCircleComponent.bounds.y = positionComponent.y;
        }

        if(boundsRectangleComponent != null){
            if(typeComponent == SpecificTypeComponent.PLANE) {
                boundsRectangleComponent.bounds.setPosition(positionComponent.x , positionComponent.y);

            }
            else {
                boundsRectangleComponent.bounds.setPosition(positionComponent.x + spriteComponent.sprite.getWidth() / (GameConfig.PPM * 2), positionComponent.y);
            }}


    }
}