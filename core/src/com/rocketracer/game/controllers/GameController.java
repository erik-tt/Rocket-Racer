package com.rocketracer.game.controllers;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rocketracer.game.ECS.Entities.BackgroundEntity;
import com.rocketracer.game.ECS.Entities.RocketEntity;
import com.rocketracer.game.ECS.Systems.ControlSystem;
import com.rocketracer.game.ECS.Systems.FuelSystem;
import com.rocketracer.game.ECS.Systems.MovementSystem;
import com.rocketracer.game.ECS.Systems.RenderSystem;
import com.rocketracer.game.ECS.Systems.BackgroundSystem;

public class GameController {
    Engine engine;
    RenderSystem renderSystem;

    ControlSystem controlSystem;

    MovementSystem movementSystem;

    RocketEntity player = new RocketEntity();
    FuelSystem fuelSystem;
    BackgroundSystem backgroundSystem;
    BackgroundEntity background = new BackgroundEntity();

    public GameController(SpriteBatch batch) {
        engine = new Engine();
        renderSystem = new RenderSystem(batch);
        controlSystem = new ControlSystem(renderSystem.getCamera());
        movementSystem = new MovementSystem();
        fuelSystem = new FuelSystem();
        backgroundSystem = new BackgroundSystem();
        engine.addSystem(renderSystem);
        engine.addSystem(fuelSystem);
        engine.addSystem(controlSystem);
        engine.addSystem(backgroundSystem);
        try {
            engine.addEntity(background.getEntity());
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
