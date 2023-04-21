package com.rocketracer.game.factory;


import com.rocketracer.game.ECS.Entities.IGameObject;

public abstract class GameObjectFactory {


    float x;
    float y;

    public GameObjectFactory(float x, float y){
            this.x = x;
            this.y = y;


    }
    public IGameObject create() {
        IGameObject object = createGameObject();
        object.build();
        return object;
    }

    protected abstract IGameObject createGameObject();

}
