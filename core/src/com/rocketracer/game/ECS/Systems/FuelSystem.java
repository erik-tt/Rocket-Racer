package com.rocketracer.game.ECS.Systems;

import static com.badlogic.gdx.math.MathUtils.random;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.rocketracer.game.ECS.Components.FuelComponent;
import com.rocketracer.game.ECS.Components.ScoreComponent;
import com.rocketracer.game.ECS.Components.TypeComponent;
import com.rocketracer.game.GameOverListener;
import com.rocketracer.game.views.GameOverView;
import com.rocketracer.game.views.GameView;

public class FuelSystem extends IteratingSystem {
    private ComponentMapper<FuelComponent> fMapper;
    private ComponentMapper<ScoreComponent> sMapper;
    private GameOverListener gameOverListener;

    public FuelSystem(GameOverListener gameOverListener) {
        super(Family.all(FuelComponent.class, ScoreComponent.class).get());
        this.gameOverListener = gameOverListener;
        fMapper = ComponentMapper.getFor(FuelComponent.class);
        sMapper = ComponentMapper.getFor(ScoreComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        FuelComponent fuel = fMapper.get(entity);
        ScoreComponent score = sMapper.get(entity);

        if(fuel.fuelLevel <= 0) {
            score.gameOver = true;
            gameOverListener.onGameOver(score.score);
        }
    }
}
