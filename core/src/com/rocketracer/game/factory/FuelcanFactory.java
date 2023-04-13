package com.rocketracer.game.factory;

import com.rocketracer.game.ECS.Entities.FuelcanEntity;
import com.rocketracer.game.ECS.Entities.IGameObject;

public class FuelcanFactory extends GameObjectFactory{
    @Override
    protected IGameObject createGameObject() {
        return new FuelcanEntity();
    }
}
