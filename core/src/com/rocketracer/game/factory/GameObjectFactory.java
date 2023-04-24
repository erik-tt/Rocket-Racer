package com.rocketracer.game.factory;


import com.rocketracer.game.ECS.Entities.IGameObject;

/*
* Abstract class for factory
 */

public abstract class GameObjectFactory {

    //Attributes
    float x;
    float y;

    //Constructor
    public GameObjectFactory(float x, float y){
            this.x = x;
            this.y = y;
    }

    //Creates GameObjects
    public IGameObject create() {
        IGameObject object = createGameObject();
        object.build();
        return object;
    }
    protected abstract IGameObject createGameObject();

}
