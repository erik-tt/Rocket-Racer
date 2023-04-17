package com.rocketracer.game.ECS.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.rocketracer.game.ECS.Components.FuelComponent;
import com.rocketracer.game.ECS.Components.PositionComponent;
import com.rocketracer.game.ECS.Components.SpriteComponent;
import com.rocketracer.game.ECS.Components.TypeComponent;

public class ControlSystem extends IteratingSystem {
    private ComponentMapper<PositionComponent> positionMapper;
    private ComponentMapper<SpriteComponent> spriteMapper;
    private ComponentMapper<TypeComponent> typeMapper;
    private OrthographicCamera camera;

    public ControlSystem(OrthographicCamera camera) {

        super(Family.all(PositionComponent.class, FuelComponent.class).get());

        positionMapper = ComponentMapper.getFor(PositionComponent.class);
        spriteMapper = ComponentMapper.getFor(SpriteComponent.class);
        typeMapper = ComponentMapper.getFor((TypeComponent.class));

        this.camera = camera;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = positionMapper.get(entity);
        SpriteComponent sprite = spriteMapper.get(entity);
        TypeComponent type = typeMapper.get(entity);



        if (Gdx.input.isTouched() && type == TypeComponent.ROCKET) {

            //Get the touch input from user.
            Vector3 touchInput = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchInput);

            //Translate the sprite width to game coordinates.
            Vector3 spriteWidth = new Vector3(sprite.sprite.getWidth(), 0, 0);
            camera.unproject(spriteWidth);

            System.out.println(spriteWidth.x);

            //Need to offset the position because of the sprite width
            if (position.x + spriteWidth.x/2 < touchInput.x) {
                position.updateX(15 * deltaTime);
            }

            //Need to offset the position because of the sprite width
            if (position.x + spriteWidth.x/2 > touchInput.x) {
                position.updateX(-15 * deltaTime);
            }
        }
    }
}
