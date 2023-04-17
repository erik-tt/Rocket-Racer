package com.rocketracer.game.factory;

import com.rocketracer.game.ECS.Entities.BirdEntity;
import com.rocketracer.game.ECS.Entities.IGameObject;

public class BirdFactory extends GameObjectFactory{
    public BirdFactory(float x, float y) {
        super(x, y);

    }

    @Override
    protected IGameObject createGameObject() {

        return new BirdEntity(x, y);
    }
}
