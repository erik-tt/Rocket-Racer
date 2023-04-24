package com.rocketracer.game.factory;

import com.rocketracer.game.ECS.Entities.IGameObject;
import com.rocketracer.game.ECS.Entities.PlaneEntity;

public class PlaneFactory extends GameObjectFactory{

    //Constructor
    public PlaneFactory(float x, float y) {
        super(x, y);
    }

    //Method for creating a new PlaneEntity
    @Override
    protected IGameObject createGameObject() {
        return new PlaneEntity(x, y);
    }
}
