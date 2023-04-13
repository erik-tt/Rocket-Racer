package com.rocketracer.game.ECS.Systems;

import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.math.MathUtils;
import com.rocketracer.game.ECS.Entities.BirdEntity;
import com.rocketracer.game.SharedData.GameConfig;
import com.rocketracer.game.factory.BirdFactory;
import com.rocketracer.game.factory.GameObjectFactory;

public class ObstacleSpawnSystem extends IntervalSystem {

    private GameObjectFactory objectFactory;


    public ObstacleSpawnSystem() {
        super(GameConfig.OBSTACLE_SPAWN_TIME);
    }

    @Override
    protected void updateInterval() {

        float min = 0;
        float max = GameConfig.FRUSTUM_WIDTH - 20;
        float obstacleX = MathUtils.random(min,max);
        float obstacleY = 20;
        objectFactory = new BirdFactory(30, 40);
        objectFactory.create();

        System.out.println(objectFactory);

    }
}
