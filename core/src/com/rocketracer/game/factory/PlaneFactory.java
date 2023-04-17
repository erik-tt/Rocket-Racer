package com.rocketracer.game.factory;

import com.rocketracer.game.ECS.Entities.IGameObject;
import com.rocketracer.game.ECS.Entities.PlaneEntity;

public class PlaneFactory extends GameObjectFactory{
    public PlaneFactory(float x, float y) {
        super(x, y);
    }

    @Override
    protected IGameObject createGameObject() {
        return new PlaneEntity(x, y);
    }
}
