package com.rocketracer.game.ECS.Entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.rocketracer.game.ECS.Components.CleanupComponent;
import com.rocketracer.game.ECS.Components.PositionComponent;
import com.rocketracer.game.ECS.Components.SpriteComponent;
import com.rocketracer.game.ECS.Components.TypeComponent;
import com.rocketracer.game.ECS.Components.VelocityComponent;

public class FuelcanEntity implements IGameObject {
    // --- Attributes ---
    private Entity entity;
    private SpriteComponent spriteComponent;
    private PositionComponent positionComponent;
    private CleanupComponent cleanupComponent;
    private VelocityComponent velocityComponent;
    private TypeComponent typeComponent;
    private Texture fuelcan = new Texture(Gdx.files.internal("fuelcan.png"));

    // --- Constructor ---
    public FuelcanEntity(float x, float y) {
        this.entity = new Entity();
        this.spriteComponent = new SpriteComponent(fuelcan);
        this.positionComponent = new PositionComponent(x, y);
        this.cleanupComponent = new CleanupComponent();
        this.velocityComponent = new VelocityComponent();
        this.typeComponent = TypeComponent.POWERUP;

        entity.add(spriteComponent);
        entity.add(positionComponent);
        entity.add(cleanupComponent);
        entity.add(velocityComponent);
        entity.add(typeComponent);
    }

    // --- Methods ---
    public Entity getEntity() {
        return entity;
    }

    @Override
    public void build() {
        System.out.println("Build fuelcan");
    }
}


