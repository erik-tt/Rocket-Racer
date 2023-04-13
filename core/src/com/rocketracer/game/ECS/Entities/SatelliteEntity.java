package com.rocketracer.game.ECS.Entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.rocketracer.game.ECS.Components.PositionComponent;
import com.rocketracer.game.ECS.Components.SpriteComponent;
import com.rocketracer.game.ECS.Components.VelocityComponent;

public class SatelliteEntity implements IGameObject {

    private Entity entity;
    private SpriteComponent spriteComponent;
    private PositionComponent positionComponent;
    private VelocityComponent velocityComponent;
    private Texture satellite = new Texture(Gdx.files.internal("satellite.png"));

    public SatelliteEntity(float x, float y){
        this.entity = new Entity();
        this.spriteComponent = new SpriteComponent(satellite);
        this.positionComponent = new PositionComponent(x,y);
        this.velocityComponent = new VelocityComponent();

        entity.add(spriteComponent);
        entity.add(positionComponent);
        entity.add(velocityComponent);
    }

    public Entity getEntity(){ return entity;}

    @Override
    public void build() {
        System.out.println("Build Satellite");
    }
}
