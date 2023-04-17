package com.rocketracer.game.factory;

import com.rocketracer.game.ECS.Entities.AsteroidEntity;
import com.rocketracer.game.ECS.Entities.IGameObject;

public class AsteroidFactory extends GameObjectFactory{
    public AsteroidFactory(float x, float y) {
        super(x, y);
    }

    @Override
    protected IGameObject createGameObject() {
        return new AsteroidEntity(x, y);
    }

}
