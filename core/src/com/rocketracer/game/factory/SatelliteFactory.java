package com.rocketracer.game.factory;

import com.rocketracer.game.ECS.Entities.IGameObject;
import com.rocketracer.game.ECS.Entities.SatelliteEntity;

public class SatelliteFactory extends GameObjectFactory{

    //Constructor
    public SatelliteFactory(float x, float y) {
        super(x, y);
    }

    //Creates a new SatelliteEntity
    @Override
    protected IGameObject createGameObject() {
        return new SatelliteEntity(x,y);
    }
}
