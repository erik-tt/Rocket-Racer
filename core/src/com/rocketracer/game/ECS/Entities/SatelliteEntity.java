package com.rocketracer.game.ECS.Entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.rocketracer.game.ECS.Components.PositionComponent;
import com.rocketracer.game.ECS.Components.SpriteComponent;
import com.rocketracer.game.ECS.Components.VelocityComponent;

public class SatelliteEntity {

    private Entity entity;
    private SpriteComponent spriteComponent;
    private PositionComponent positionComponent;
    private VelocityComponent velocityComponent;
    private Texture satellite = new Texture(Gdx.files.internal("satellite.png"));

    public SatelliteEntity(){
        this.entity = new Entity();
        this.spriteComponent = new SpriteComponent(satellite);
        this.positionComponent = new PositionComponent(0,0);
        this.velocityComponent = new VelocityComponent(0,0);

        entity.add(spriteComponent);
        entity.add(positionComponent);
        entity.add(velocityComponent);
    }

    public Entity getEntity(){ return entity;}
}
