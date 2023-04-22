package com.rocketracer.game.ECS.Entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.rocketracer.game.ECS.Components.BackgroundComponent;
import com.rocketracer.game.ECS.Components.PositionComponent;
import com.rocketracer.game.ECS.Components.SpriteComponent;
import com.rocketracer.game.ECS.Components.VelocityComponent;

public class BackgroundEntity implements IGameObject {
    // Attributes
    private Entity entity;
    private SpriteComponent spriteComponent;
    private PositionComponent positionComponent;
    private VelocityComponent velocityComponent;
    private BackgroundComponent backgroundComponent;
    private Texture background = new Texture(Gdx.files.internal("background.png"));

    //Constructor
    public BackgroundEntity(){
        this.entity = new Entity();

        //Initiate entity with components
        this.spriteComponent = new SpriteComponent(background);
        this.positionComponent = new PositionComponent(0,0);
        this.velocityComponent = new VelocityComponent();
        this.backgroundComponent = new BackgroundComponent(0,(spriteComponent.sprite.getHeight()),2);

        //Add components to entity
        entity.add(spriteComponent);
        entity.add(positionComponent);
        entity.add(velocityComponent);
        entity.add(backgroundComponent);
    }

    // Methods
    public Entity getEntity(){ return entity;}

    @Override
    public void build() {
        System.out.println("Build background");
    }
}
