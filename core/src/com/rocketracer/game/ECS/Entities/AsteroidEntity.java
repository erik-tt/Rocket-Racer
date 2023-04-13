package com.rocketracer.game.ECS.Entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.rocketracer.game.ECS.Components.PositionComponent;
import com.rocketracer.game.ECS.Components.SpriteComponent;
import com.rocketracer.game.ECS.Components.VelocityComponent;



public class AsteroidEntity {

    private Entity entity;
    private SpriteComponent spriteComponent;
    private PositionComponent positionComponent;
    private VelocityComponent velocityComponent;
    private Texture asteroid = new Texture(Gdx.files.internal("asteroid.png"));

    public AsteroidEntity(){
        this.entity = new Entity();
        this.spriteComponent = new SpriteComponent(asteroid);
        this.positionComponent = new PositionComponent(0,0);
        this.velocityComponent = new VelocityComponent(0,0);

        entity.add(spriteComponent);
        entity.add(positionComponent);
        entity.add(velocityComponent);
    }

    public Entity getEntity(){ return entity;}

}
