package com.rocketracer.game.factory;

import com.rocketracer.game.ECS.Entities.IGameObject;
import com.rocketracer.game.ECS.Entities.SatelliteEntity;

public class SatelliteFactory extends GameObjectFactory{
    @Override
    protected IGameObject createGameObject() {
        return new SatelliteEntity();
    }
}
