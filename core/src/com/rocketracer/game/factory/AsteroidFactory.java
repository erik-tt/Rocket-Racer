package com.rocketracer.game.factory;

import com.rocketracer.game.ECS.Entities.AsteroidEntity;
import com.rocketracer.game.ECS.Entities.IGameObject;

public class AsteroidFactory extends GameObjectFactory{
    @Override
    protected IGameObject createGameObject() {
        return new AsteroidEntity();
    }

}
