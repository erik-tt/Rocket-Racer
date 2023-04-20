package com.rocketracer.game.controllers;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rocketracer.game.ECS.Entities.BackgroundEntity;

import com.rocketracer.game.ECS.Entities.RocketEntity;
import com.rocketracer.game.ECS.Systems.CleanupSystem;
import com.rocketracer.game.ECS.Systems.CollisionListener;
import com.rocketracer.game.ECS.Systems.CollisionSystem;
import com.rocketracer.game.ECS.Systems.ControlSystem;
import com.rocketracer.game.ECS.Systems.FuelSystem;
import com.rocketracer.game.ECS.Systems.MovementSystem;
import com.rocketracer.game.ECS.Systems.ObstacleSpawnSystem;
import com.rocketracer.game.ECS.Systems.RenderSystem;
import com.rocketracer.game.ECS.Systems.BackgroundSystem;
import com.rocketracer.game.ECS.Systems.ScoreSystem;
import com.rocketracer.game.GameEventListener;
import com.rocketracer.game.GameOverListener;
import com.rocketracer.game.SharedData.HighScoreList;
import com.rocketracer.game.SharedData.LocalData;
import com.rocketracer.game.views.GameOverView;

import java.util.Map;


public class GameController implements GameEventListener, GameOverListener {
    private Engine engine;
    private RenderSystem renderSystem;

    private ControlSystem controlSystem;

    private MovementSystem movementSystem;

    private RocketEntity player = new RocketEntity();
    private FuelSystem fuelSystem;
    private ObstacleSpawnSystem obstacleSpawnSystem;
    private CleanupSystem cleanupSystem;
    private CollisionSystem collisionSystem;

    private BackgroundSystem backgroundSystem;
    private ScoreSystem scoreSystem;
    private BackgroundEntity background = new BackgroundEntity();


    // GameEvent
    private String docID;
    private Boolean isMP;


    public GameController(SpriteBatch batch, Boolean isMP, String docID) {
        //Create the game engine:
        engine = new Engine();

        // Multiplayer
        this.docID = docID;
        this.isMP = isMP;
        if (isMP && docID != null) LocalData.sharedInstance.getFBIHandler().setGameListener(this);

        //Create the systems
        renderSystem = new RenderSystem(batch);
        controlSystem = new ControlSystem(renderSystem.getCamera());
        movementSystem = new MovementSystem();
        obstacleSpawnSystem = new ObstacleSpawnSystem(engine);
        cleanupSystem = new CleanupSystem(engine);
        fuelSystem = new FuelSystem(this);
        scoreSystem = new ScoreSystem();

        backgroundSystem = new BackgroundSystem();
        engine.addSystem(renderSystem);
        engine.addSystem(fuelSystem);
        engine.addSystem(controlSystem);
        engine.addSystem(backgroundSystem);

        scoreSystem = new ScoreSystem();
        engine.addSystem(renderSystem);
        engine.addSystem(fuelSystem);
        engine.addSystem(controlSystem);
        engine.addSystem(scoreSystem);

        try {
            engine.addEntity(background.getEntity());
        } catch (IllegalArgumentException ie) {
            System.out.println(ie.getMessage());
        }

        CollisionListener listener = new CollisionListener() {
            @Override
            public void hitObstacle() {
                System.out.println("Bird hit");
            }
        };
        collisionSystem = new CollisionSystem(listener);

        //Add the systems to the engine
        engine.addSystem(obstacleSpawnSystem);
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

    @Override
    public String getDocID() {
        return this.docID;
    }

    @Override
    public void newSnapshotData(Map<String, Object> data) {

    }

    // -- GameOverListener impl --
    @Override
    public void onGameOver(Integer score) {
        if (isMP) {
            LocalData.sharedInstance.getFBIHandler().setGameScore(score);
            LocalData.sharedInstance.getFBIHandler().updateHighscoreList(LocalData.sharedInstance.playerName, score);
        } else HighScoreList.sharedInstance.addSPScore(LocalData.sharedInstance.playerName, score);

        ((Game) Gdx.app.getApplicationListener()).setScreen(new GameOverView(score, docID));
    }
}
