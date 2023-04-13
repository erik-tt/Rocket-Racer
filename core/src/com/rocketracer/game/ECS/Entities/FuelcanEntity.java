package com.rocketracer.game.ECS.Entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.rocketracer.game.ECS.Components.PositionComponent;
import com.rocketracer.game.ECS.Components.SpriteComponent;

public class FuelcanEntity {
    // --- Attributes ---
    private Entity entity;
    private SpriteComponent spriteComponent;
    private PositionComponent positionComponent;
    private Texture fuelcan = new Texture(Gdx.files.internal("fuelcan.png"));

    // --- Constructor ---
    public FuelcanEntity() {
        this.entity = new Entity();
        this.spriteComponent = new SpriteComponent(fuelcan);
        this.positionComponent = new PositionComponent(0, 0);

        entity.add(spriteComponent);
        entity.add(positionComponent);
    }

    // --- Methods ---
    public Entity getEntity() {
        return entity;
    }
}


