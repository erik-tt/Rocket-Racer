package com.rocketracer.game.controllers;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rocketracer.game.ECS.Entities.RocketEntity;
import com.rocketracer.game.ECS.Systems.ControlSystem;
import com.rocketracer.game.ECS.Systems.FuelSystem;
import com.rocketracer.game.ECS.Systems.MovementSystem;
import com.rocketracer.game.ECS.Systems.RenderSystem;
import com.rocketracer.game.ECS.Systems.ScoreSystem;

public class GameController {
    Engine engine;
    RenderSystem renderSystem;

    ControlSystem controlSystem;

    MovementSystem movementSystem;

    RocketEntity player = new RocketEntity();
    FuelSystem fuelSystem;
    ScoreSystem scoreSystem;


    public GameController(SpriteBatch batch) {
        engine = new Engine();
        renderSystem = new RenderSystem(batch);
        controlSystem = new ControlSystem(renderSystem.getCamera());
        movementSystem = new MovementSystem();
        fuelSystem = new FuelSystem();
        scoreSystem = new ScoreSystem();
        engine.addSystem(renderSystem);
        engine.addSystem(fuelSystem);
        engine.addSystem(controlSystem);
        engine.addSystem(scoreSystem);
        try {

            engine.addEntity(player.getEntity());
            System.out.println("Success in adding player entity to engine.");
        } catch (IllegalArgumentException ie) {
            System.out.println(ie.getMessage());
        }
    }

    public Engine getEngine(){
        return engine;
    }

}
