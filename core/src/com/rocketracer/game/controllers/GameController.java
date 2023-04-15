package com.rocketracer.game.controllers;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rocketracer.game.ECS.Entities.RocketEntity;
import com.rocketracer.game.ECS.Systems.CleanupSystem;
import com.rocketracer.game.ECS.Systems.CollisionListener;
import com.rocketracer.game.ECS.Systems.CollisionSystem;
import com.rocketracer.game.ECS.Systems.ControlSystem;
import com.rocketracer.game.ECS.Systems.FuelSystem;
import com.rocketracer.game.ECS.Systems.MovementSystem;
import com.rocketracer.game.ECS.Systems.ObstacleSpawnSystem;
import com.rocketracer.game.ECS.Systems.RenderSystem;

public class GameController {
    private Engine engine;
    private RenderSystem renderSystem;

    private ControlSystem controlSystem;

    private MovementSystem movementSystem;

    private RocketEntity player = new RocketEntity();
    private FuelSystem fuelSystem;
    private ObstacleSpawnSystem obstacleSpawnSystem;
    private CleanupSystem cleanupSystem;
    private CollisionSystem collisionSystem;



    public GameController(SpriteBatch batch) {

        //Create the game engine:
        engine = new Engine();

        //Create the systems
        renderSystem = new RenderSystem(batch);
        controlSystem = new ControlSystem(renderSystem.getCamera());
        movementSystem = new MovementSystem();
        obstacleSpawnSystem = new ObstacleSpawnSystem(engine);
        cleanupSystem = new CleanupSystem(engine);
        fuelSystem = new FuelSystem();

        CollisionListener listener = new CollisionListener() {
            @Override
            public void hitObstacle() {
                System.out.println("Bird hit");

            }};
        collisionSystem = new CollisionSystem(listener);


        //Add the systems to the engine
        engine.addSystem(fuelSystem);
        engine.addSystem(controlSystem);
        engine.addSystem(obstacleSpawnSystem);
        engine.addSystem(renderSystem);
        engine.addSystem(movementSystem);
        engine.addSystem(cleanupSystem);
        engine.addSystem(collisionSystem);

        //Add the player entity to the engine
        try {
            engine.addEntity(player.getEntity());

        } catch (IllegalArgumentException ie) {
            System.out.println(ie.getMessage());
        }

    }

    public Engine getEngine(){
        return engine;
    }

}
