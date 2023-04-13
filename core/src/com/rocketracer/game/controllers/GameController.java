package com.rocketracer.game.controllers;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rocketracer.game.ECS.Entities.BirdEntity;
import com.rocketracer.game.ECS.Entities.RocketEntity;
import com.rocketracer.game.ECS.Systems.ControlSystem;
import com.rocketracer.game.ECS.Systems.FuelSystem;
import com.rocketracer.game.ECS.Systems.MovementSystem;
import com.rocketracer.game.ECS.Systems.ObstacleSpawnSystem;
import com.rocketracer.game.ECS.Systems.RenderSystem;

public class GameController {
    Engine engine;
    RenderSystem renderSystem;

    ControlSystem controlSystem;

    MovementSystem movementSystem;

    RocketEntity player = new RocketEntity();
    FuelSystem fuelSystem;
    ObstacleSpawnSystem obstacleSpawnSystem;



    public GameController(SpriteBatch batch) {
        engine = new Engine();
        renderSystem = new RenderSystem(batch);
        controlSystem = new ControlSystem(renderSystem.getCamera());
        movementSystem = new MovementSystem();
        obstacleSpawnSystem = new ObstacleSpawnSystem();
        fuelSystem = new FuelSystem();

        engine.addSystem(fuelSystem);
        engine.addSystem(controlSystem);
        engine.addSystem(obstacleSpawnSystem);
        engine.addSystem(renderSystem);
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
