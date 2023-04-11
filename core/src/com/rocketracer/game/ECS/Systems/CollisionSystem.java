package com.rocketracer.game.ECS.Systems;


import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.rocketracer.game.ECS.Components.CollisionComponent;
import com.rocketracer.game.ECS.Components.FuelComponent;
import com.rocketracer.game.ECS.Components.TypeComponent;

/**
 * The collision system uses inspiration from this game tutorial:
 * https://www.gamedevelopment.blog/full-libgdx-game-tutorial-game-mechanics/
 * gathered 11.04.2023
 */
public class CollisionSystem extends IteratingSystem {

    private ComponentMapper<CollisionComponent> collisionComponent;
    private final ComponentMapper<FuelComponent> playerComponent;

    /**
     * Get all the components that has collided with the rocket and
     * the one with a fuel component, because it is the rocket.
     */
    public CollisionSystem() {
        super(Family.all(CollisionComponent.class, FuelComponent.class).get());

        collisionComponent = ComponentMapper.getFor(CollisionComponent.class);
        playerComponent = ComponentMapper.getFor(FuelComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CollisionComponent cComponent = collisionComponent.get(entity);
        Entity collisionEntity = cComponent.collidedComponent;

        if (collisionEntity != null) {
            TypeComponent type = collisionEntity.getComponent(TypeComponent.class);

            if (type == TypeComponent.OBSTACLE) {
                //Reduce the fuel level by a given amount, 20 in this case.
                playerComponent.get(entity).fuelLevel = playerComponent.get(entity).fuelLevel - 20;
                System.out.println("Hit obstacle");
            }

            if (type == TypeComponent.POWERUP) {
                //Increase the fuel level by a given amount, 20 in this case.
                playerComponent.get(entity).fuelLevel = playerComponent.get(entity).fuelLevel + 20;
                System.out.println("Hit power up");
            }

            else {
                System.out.println("Type does not exist");
            }
            //Reset after the collision is handled.
            cComponent.collidedComponent = null;

        }

    }
}
