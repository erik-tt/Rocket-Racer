package com.rocketracer.game.ECS.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.rocketracer.game.ECS.Components.PositionComponent;
import com.rocketracer.game.ECS.Components.SpriteComponent;

public class ControlSystem extends IteratingSystem {
    private ComponentMapper<PositionComponent> positionMapper;
    private ComponentMapper<SpriteComponent> spriteMapper;
    private OrthographicCamera camera;

    public ControlSystem(OrthographicCamera camera) {
        super(Family.all(PositionComponent.class).get());
        positionMapper = ComponentMapper.getFor(PositionComponent.class);
        spriteMapper = ComponentMapper.getFor(SpriteComponent.class);
        this.camera = camera;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = positionMapper.get(entity);
        SpriteComponent sprite = spriteMapper.get(entity);



        if (Gdx.input.isTouched()) {

            //Get the touch input from user.
            Vector3 touchInput = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchInput);

            //Translate the sprite width to game coordinates.
            Vector3 spriteWidth = new Vector3(sprite.sprite.getWidth(), 0, 0);
            camera.unproject(spriteWidth);

            System.out.println(spriteWidth.x);

            //Need to offset the position because of the sprite width
            if (position.x + spriteWidth.x/2 < touchInput.x) {
                position.updateX(10 * deltaTime);
            }

            //Need to offset the position because of the sprite width
            if (position.x + spriteWidth.x/2 > touchInput.x) {
                position.updateX(-10 * deltaTime);
            }
        }
    }
}
