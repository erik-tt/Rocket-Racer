package com.rocketracer.game.ECS.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.rocketracer.game.ECS.Components.PositionComponent;
import com.rocketracer.game.ECS.Components.SpriteComponent;

public class ControlSystem extends IteratingSystem {
    private ComponentMapper<PositionComponent> positionMapper;
    private ComponentMapper<SpriteComponent> spriteMapper;

    public ControlSystem() {
        super(Family.all(PositionComponent.class).get());
        positionMapper = ComponentMapper.getFor(PositionComponent.class);
        spriteMapper = ComponentMapper.getFor(SpriteComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = positionMapper.get(entity);
        SpriteComponent sprite = spriteMapper.get(entity);

        if (Gdx.input.isTouched()) {
            position.updateXWith(Gdx.input.getX(), sprite.sprite.getWidth());
        }
    }
}
