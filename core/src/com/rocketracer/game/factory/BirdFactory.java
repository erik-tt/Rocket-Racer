package com.rocketracer.game.factory;

import com.rocketracer.game.ECS.Entities.BirdEntity;
import com.rocketracer.game.ECS.Entities.IGameObject;

public class BirdFactory extends GameObjectFactory{

    //Attributes
    public BirdFactory(float x, float y) {
        super(x, y);

    }

    //Method for creating a BirdEntity
    @Override
    protected IGameObject createGameObject() {

        return new BirdEntity(x, y);
    }
}
