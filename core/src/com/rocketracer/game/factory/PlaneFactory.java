package com.rocketracer.game.factory;

import com.rocketracer.game.ECS.Entities.IGameObject;
import com.rocketracer.game.ECS.Entities.PlaneEntity;

public class PlaneFactory extends GameObjectFactory{
    @Override
    protected IGameObject createGameObject() {
        return new PlaneEntity();
    }
}
