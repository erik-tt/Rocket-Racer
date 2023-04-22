package com.rocketracer.game.ECS.Systems;

import com.badlogic.ashley.core.ComponentMapper;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;

import com.badlogic.ashley.systems.IteratingSystem;
import com.rocketracer.game.ECS.Components.FuelComponent;
import com.rocketracer.game.ECS.Components.ScoreComponent;
import com.rocketracer.game.GameOverListener;

/**
 * Deals with the fuel in the game.
 */

public class FuelSystem extends IteratingSystem {

    //Attributes
    private ComponentMapper<FuelComponent> fMapper;
    private ComponentMapper<ScoreComponent> sMapper;
    private GameOverListener gameOverListener;

    //Constructors
    public FuelSystem(GameOverListener gameOverListener) {
        super(Family.all(FuelComponent.class, ScoreComponent.class).get());
        this.gameOverListener = gameOverListener;
        fMapper = ComponentMapper.getFor(FuelComponent.class);
        sMapper = ComponentMapper.getFor(ScoreComponent.class);
    }

    //Method
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        FuelComponent fuel = fMapper.get(entity);
        ScoreComponent score = sMapper.get(entity);

        //When fuel level is 0 sets game over
        if(fuel.fuelLevel <= 0) {
            score.gameOver = true;
            gameOverListener.onGameOver(score.score);
        }
    }
}
