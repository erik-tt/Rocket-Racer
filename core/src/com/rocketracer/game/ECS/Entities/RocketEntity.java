package com.rocketracer.game.ECS.Entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.rocketracer.game.ECS.Components.*;
import com.rocketracer.game.SharedData.GameConfig;

public class RocketEntity {
    // --- Attributes ---
    private Entity entity;
    private SpriteComponent spriteComponent;
    private PositionComponent positionComponent;
    private FuelComponent fuelComponent;
    private BoundsComponent boundsComponent;
    private Texture rocket = new Texture(Gdx.files.internal("Rocket1.png"));

    private TypeComponent typeComponent;

    // --- Constructor ---
    public RocketEntity() {
        this.entity = new Entity();
        this.spriteComponent = new SpriteComponent(rocket);
        this.positionComponent = new PositionComponent(GameConfig.FRUSTUM_WIDTH/2, 0);
        this.fuelComponent = new FuelComponent(100);
        this.typeComponent = TypeComponent.ROCKET;
        this.boundsComponent = new BoundsComponent();
        boundsComponent.bounds.x = positionComponent.x;
        boundsComponent.bounds.y = spriteComponent.texture.getHeight()-(spriteComponent.texture.getWidth()/(GameConfig.PPM*2));
        boundsComponent.bounds.radius = spriteComponent.texture.getWidth()/(GameConfig.PPM*2);

        System.out.println("Rocket" + boundsComponent.bounds.y);
        entity.add(fuelComponent);
        entity.add(spriteComponent);
        entity.add(positionComponent);
        entity.add(typeComponent);
        entity.add(boundsComponent);

    }

    // --- Methods ---
    public Entity getEntity() {
        return entity;
    }
}
