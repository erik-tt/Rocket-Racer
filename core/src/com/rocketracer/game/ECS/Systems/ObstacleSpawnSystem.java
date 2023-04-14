package com.rocketracer.game.ECS.Systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.math.MathUtils;
import com.rocketracer.game.SharedData.GameConfig;
import com.rocketracer.game.factory.BirdFactory;
import com.rocketracer.game.factory.GameObjectFactory;

public class ObstacleSpawnSystem extends IntervalSystem {

    private GameObjectFactory objectFactory;
    private Engine engine;


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
        objectFactory = new BirdFactory(obstacleX, obstacleY);
        Entity bird = objectFactory.create().getEntity();
        engine.addEntity(bird);

        System.out.println(objectFactory);

    }
}
