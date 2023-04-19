package com.rocketracer.game.ECS.Systems;

import static junit.framework.TestCase.assertEquals;

import com.badlogic.ashley.core.Engine;
import com.rocketracer.game.ECS.Components.BoundsCircleComponent;
import com.rocketracer.game.ECS.Components.BoundsRectangleComponent;
import com.rocketracer.game.ECS.Components.CollisionComponent;
import com.rocketracer.game.ECS.Components.FuelComponent;
import com.rocketracer.game.ECS.Components.PositionComponent;
import com.rocketracer.game.ECS.Components.SpecificTypeComponent;
import com.rocketracer.game.ECS.Components.TypeComponent;
import com.rocketracer.game.ECS.Entities.TestEntity;

import org.junit.Before;
import org.junit.Test;


public class CollisionSystemTest {

    private CollisionSystem collisionSystem;
    private TestEntity player;
    private TestEntity obstacle;
    private Engine testEngin;

    @Before
    public void setup() {

        CollisionListener listener = new CollisionListener() {
            @Override
            public void hitObstacle() {
                System.out.println("Obstacle hit");


            }};

        collisionSystem = new CollisionSystem(listener);

        player = new TestEntity();
        TypeComponent playerTypeComponent = TypeComponent.ROCKET;
        //Has to use plane to avoid sprite offsetting in collision system:
        SpecificTypeComponent specificTypeComponent = SpecificTypeComponent.PLANE;
        player.setSpecificTypeComponent(specificTypeComponent);
        FuelComponent fuelComponent = new FuelComponent(100);
        player.setTypeComponent(playerTypeComponent);
        player.setFuelComponent(fuelComponent);
        BoundsRectangleComponent boundsRectangleComponent = new BoundsRectangleComponent(5, 5);
        player.setBoundsRectangleComponent(boundsRectangleComponent);
        PositionComponent positionComponent = new PositionComponent(0,0);
        player.setPositionComponent(positionComponent);


        obstacle = new TestEntity();
        TypeComponent obstacleComponent = TypeComponent.OBSTACLE;
        obstacle.setSpecificTypeComponent(SpecificTypeComponent.PLANE);
        obstacle.setTypeComponent(obstacleComponent);
        CollisionComponent collisionComponent = new CollisionComponent();
        obstacle.setCollisionComponent(collisionComponent);
        BoundsRectangleComponent boundsRectangleComponent2 = new BoundsRectangleComponent(5, 5);
        obstacle.setBoundsRectangleComponent(boundsRectangleComponent2);
        PositionComponent positionComponent2 = new PositionComponent(0,0);
        obstacle.setPositionComponent(positionComponent2);

        testEngin = new Engine();
        testEngin.addSystem(collisionSystem);
        testEngin.addEntity(player.getEntity());
        testEngin.addEntity(obstacle.getEntity());
    }

    @Test
    public  void testCollisionByPlane() {
        collisionSystem.update(0);

        int fuelLevelAfterPlaneHit =  90;
        //Test fuel level
        assertEquals(fuelLevelAfterPlaneHit, player.getFuelComponent().fuelLevel);

        //Check that it does not hit while they still intersect.
        collisionSystem.update(0);
        assertEquals(fuelLevelAfterPlaneHit, player.getFuelComponent().fuelLevel);

        obstacle.getCollisionComponent().hit = false;
        collisionSystem.update(0);
        fuelLevelAfterPlaneHit = 80;
        assertEquals(fuelLevelAfterPlaneHit, player.getFuelComponent().fuelLevel);
    }

}
