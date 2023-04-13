package com.rocketracer.game.factory;


import com.rocketracer.game.ECS.Entities.IGameObject;

public abstract class GameObjectFactory {
    public IGameObject create() {
        IGameObject object = createGameObject();
        object.build();
        return object;
    }

    protected abstract IGameObject createGameObject();
}
