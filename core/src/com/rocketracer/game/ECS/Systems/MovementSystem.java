package com.rocketracer.game.ECS.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.rocketracer.game.ECS.Components.PositionComponent;
import com.rocketracer.game.ECS.Components.SpriteComponent;
import com.rocketracer.game.ECS.Components.TypeComponent;
import com.rocketracer.game.ECS.Components.VelocityComponent;
import com.rocketracer.game.SharedData.GameConfig;

import static com.badlogic.gdx.math.MathUtils.random;

public class MovementSystem extends IteratingSystem {

    private ComponentMapper<VelocityComponent> vMapper;
    private ComponentMapper<TypeComponent> tMapper;
    private ComponentMapper<PositionComponent> pMapper;
    private ComponentMapper<SpriteComponent> sMapper;

    public MovementSystem() {
        super(Family.all(VelocityComponent.class, TypeComponent.class, PositionComponent.class, SpriteComponent.class).get());
        vMapper = ComponentMapper.getFor(VelocityComponent.class);
        tMapper = ComponentMapper.getFor(TypeComponent.class);
        pMapper = ComponentMapper.getFor(PositionComponent.class);
        sMapper = ComponentMapper.getFor(SpriteComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TypeComponent type = tMapper.get(entity);
        VelocityComponent velocity = vMapper.get(entity);
        PositionComponent position = pMapper.get(entity);
        SpriteComponent sprite = sMapper.get(entity);

        //TODO
        //Create a more complex movement system based on type and gameplay difficulty

        if (type == TypeComponent.OBSTACLE) {
            //Just a random number from 1-10 for the obstacle speed in y direction.
            if (!velocity.isSpeedSet()) {
                //Chose the X sign
                int sign = random.nextInt(2);
                int speedX = 0;

                if (sign == 1) {
                    speedX = random.nextInt(10);
                }

                else {
                    speedX = - random.nextInt(10);
                    sprite.sprite.flip(true, false);
                }

                int speedY = random.nextInt(30);
                velocity.setSpeed(speedX, speedY);
            }



            position.x += velocity.x * deltaTime;
            position.y -= velocity.y * deltaTime;

        }

    }
}
