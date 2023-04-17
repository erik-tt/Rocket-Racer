package com.rocketracer.game.ECS.Systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.math.MathUtils;
import com.rocketracer.game.SharedData.GameConfig;
import com.rocketracer.game.factory.AsteroidFactory;
import com.rocketracer.game.factory.BirdFactory;
import com.rocketracer.game.factory.FuelcanFactory;
import com.rocketracer.game.factory.GameObjectFactory;
import com.rocketracer.game.factory.PlaneFactory;
import com.rocketracer.game.factory.SatelliteFactory;
import com.sun.org.apache.bcel.internal.generic.SWITCH;

public class ObstacleSpawnSystem extends IntervalSystem {

    private GameObjectFactory objectFactory;
    private Engine engine;

    private int counter = 0;
    private int fuelSpawnRate = 23;
    private int obstacleSpawnRate = 1;


    public ObstacleSpawnSystem(Engine engine) {
        super(GameConfig.OBSTACLE_SPAWN_TIME);
        this.engine = engine;
    }

    @Override
    protected void updateInterval() {

        float min = 0;
        float max = GameConfig.FRUSTUM_WIDTH ;
        float obstacleX = MathUtils.random(min,max);
        float obstacleY = GameConfig.FRUSTUM_HEIGHT;

        switch (GameConfig.DIFFICULTY) {

            case 1:
                objectFactory = new BirdFactory(obstacleX, obstacleY);
                Entity bird = objectFactory.create().getEntity();
                engine.addEntity(bird);
                break;

            case 2:
                obstacleSpawnRate = 2;
                if (counter % 2 == 0) {
                    objectFactory = new PlaneFactory(obstacleX, obstacleY);
                    Entity plane = objectFactory.create().getEntity();
                    engine.addEntity(plane);
                }
                break;

            case 3:
                obstacleSpawnRate = 2;
                if (counter % 2 == 0) {
                    objectFactory = new SatelliteFactory(obstacleX, obstacleY);
                    Entity satellite = objectFactory.create().getEntity();
                    engine.addEntity(satellite);
                }
                break;

            case 4:
                obstacleSpawnRate = 1;
                objectFactory = new AsteroidFactory(obstacleX, obstacleY);
                Entity asteroid = objectFactory.create().getEntity();
                engine.addEntity(asteroid);
                break;
        }

        //Spawn fuelcan
        if (counter % fuelSpawnRate == 0) {
            objectFactory = new FuelcanFactory(obstacleX, obstacleY);
            Entity fuelcan = objectFactory.create().getEntity();
            engine.addEntity(fuelcan);
        }
        counter ++;
    }
}
