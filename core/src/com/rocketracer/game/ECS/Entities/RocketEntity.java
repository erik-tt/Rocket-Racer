package com.rocketracer.game.ECS.Entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.rocketracer.game.ECS.Components.*;

public class RocketEntity {
    // --- Attributes ---
    private Entity entity;
    private SpriteComponent spriteComponent;
    private PositionComponent positionComponent;
    private Texture rocket = new Texture(Gdx.files.internal("Rocket1.png"));

    // --- Constructor ---
    public RocketEntity() {
        this.entity = new Entity();
        this.spriteComponent = new SpriteComponent(rocket, 1);
        this.positionComponent = new PositionComponent(12, 0);

        entity.add(spriteComponent);
        entity.add(positionComponent);
    }

    // --- Methods ---
    public Entity getEntity() {
        return entity;
    }
}
