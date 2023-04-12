package com.rocketracer.game.ECS.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.rocketracer.game.ECS.Components.PositionComponent;
import com.rocketracer.game.ECS.Components.SpriteComponent;
import com.rocketracer.game.SharedData.Utilites;

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
            Vector3 touchInput = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            Utilites.getCamera().unproject(touchInput);
            float touchX = touchInput.x;

            System.out.println(touchX);

            if (position.x < touchX) {
                position.updateXWith(10 * deltaTime, sprite.sprite.getWidth());
            }
            if (position.x > touchX) {
                position.updateXWith(-10 * deltaTime, sprite.sprite.getWidth());
            }
        }
    }
}
