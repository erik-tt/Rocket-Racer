package com.rocketracer.game.ECS.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Intersector;
import com.rocketracer.game.ECS.Components.BoundsComponent;
import com.rocketracer.game.ECS.Components.CollisionComponent;
import com.rocketracer.game.ECS.Components.FuelComponent;
import com.rocketracer.game.ECS.Components.TypeComponent;

/**
 * Deals with the collisions in the game.
 */

public class CollisionSystem extends EntitySystem {


    /*
     * We need to families here which will contain the entities that can collide
     */
    private static final Family PLAYER_FAMILY = Family.all(
            FuelComponent.class,
            BoundsComponent.class).get();

    private static final Family OBSTACLE_FAMILY = Family.all(
            TypeComponent.class,
            CollisionComponent.class,
            BoundsComponent.class).get();


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
                if (collisionComponent.hit) {
                    continue;
                }

                if (checkCollision(playerEntity, obstacleEntity)) {
                    collisionComponent.hit = true;
                    System.out.println("Collision detected!");
                    listener.hitObstacle();
                }
            }

        }
    }

    private boolean checkCollision(Entity playerEntity, Entity obstacleEntity) {
        BoundsComponent playerBounds = ComponentMapper.getFor(BoundsComponent.class).get(playerEntity);
        BoundsComponent obstacleBounds = ComponentMapper.getFor(BoundsComponent.class).get(obstacleEntity);

        return Intersector.overlaps(playerBounds.bounds, obstacleBounds.bounds);
    }
}