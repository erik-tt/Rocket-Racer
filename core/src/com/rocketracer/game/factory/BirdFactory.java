package com.rocketracer.game.factory;

import com.rocketracer.game.ECS.Entities.BirdEntity;
import com.rocketracer.game.ECS.Entities.IGameObject;

public class BirdFactory extends GameObjectFactory{
    @Override
    protected IGameObject createGameObject() {
        return new BirdEntity();
    }
}
