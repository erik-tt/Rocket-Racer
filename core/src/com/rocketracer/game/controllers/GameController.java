package com.rocketracer.game.controllers;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rocketracer.game.ECS.Entities.BackgroundEntity;
import com.rocketracer.game.ECS.Entities.RocketEntity;
import com.rocketracer.game.ECS.Systems.CleanupSystem;
import com.rocketracer.game.ECS.Systems.ControlSystem;
import com.rocketracer.game.ECS.Systems.FuelSystem;
import com.rocketracer.game.ECS.Systems.MovementSystem;
import com.rocketracer.game.ECS.Systems.ObstacleSpawnSystem;
import com.rocketracer.game.ECS.Systems.RenderSystem;
import com.rocketracer.game.ECS.Systems.BackgroundSystem;

public class GameController {
    private Engine engine;
    private RenderSystem renderSystem;

    private ControlSystem controlSystem;

    private MovementSystem movementSystem;

    private RocketEntity player = new RocketEntity();
    private FuelSystem fuelSystem;
    private ObstacleSpawnSystem obstacleSpawnSystem;
    private CleanupSystem cleanupSystem;

    private BackgroundSystem backgroundSystem;
    private BackgroundEntity background = new BackgroundEntity();


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

        backgroundSystem = new BackgroundSystem();
        engine.addSystem(renderSystem);
        engine.addSystem(fuelSystem);
        engine.addSystem(controlSystem);
        engine.addSystem(backgroundSystem);
        try {
            engine.addEntity(background.getEntity());

        } catch (IllegalArgumentException ie) {
            System.out.println(ie.getMessage());
        }



        //Add the systems to the engine
        engine.addSystem(obstacleSpawnSystem);
        engine.addSystem(movementSystem);
        engine.addSystem(cleanupSystem);

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
