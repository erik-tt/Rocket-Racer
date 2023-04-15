package com.rocketracer.game.ECS.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.rocketracer.game.ECS.Components.PositionComponent;
import com.rocketracer.game.ECS.Components.SpecificTypeComponent;
import com.rocketracer.game.ECS.Components.SpriteComponent;
import com.rocketracer.game.ECS.Components.TypeComponent;
import com.rocketracer.game.ECS.Components.VelocityComponent;
import com.rocketracer.game.SharedData.GameConfig;

import static com.badlogic.gdx.math.MathUtils.random;

public class MovementSystem extends IteratingSystem {

    private ComponentMapper<VelocityComponent> vMapper;
    private ComponentMapper<SpecificTypeComponent> tMapper;
    private ComponentMapper<PositionComponent> pMapper;
    private ComponentMapper<SpriteComponent> sMapper;

    public MovementSystem() {
        super(Family.all(VelocityComponent.class, SpecificTypeComponent.class, PositionComponent.class, SpriteComponent.class).get());

        vMapper = ComponentMapper.getFor(VelocityComponent.class);
        tMapper = ComponentMapper.getFor(SpecificTypeComponent.class);
        pMapper = ComponentMapper.getFor(PositionComponent.class);
        sMapper = ComponentMapper.getFor(SpriteComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        SpecificTypeComponent type = tMapper.get(entity);
        VelocityComponent velocity = vMapper.get(entity);
        PositionComponent position = pMapper.get(entity);
        SpriteComponent sprite = sMapper.get(entity);

        //TODO
        //Create a more complex movement system based on type and gameplay difficulty

        //Just a random number from 1-10 for the obstacle speed in y direction.
        if (!velocity.isSpeedSet()) {
            int speedX = 0;
            int speedY = 0;

            if (type == SpecificTypeComponent.ASTEROID || type == SpecificTypeComponent.BIRD ||
            type == SpecificTypeComponent.PLANE || type == SpecificTypeComponent.SATELLITE) {
                int sign = random.nextInt(2);
                //Chose the X sign
                if (sign == 1) {
                    speedX = random.nextInt(10);
                } else {
                    speedX = -random.nextInt(10);
                    sprite.sprite.flip(true, false);
                }
            }

            //Determine special case speed:
            switch (type) {
                case BIRD:
                    speedY = random.nextInt(30);
                    break;
                case PLANE:
                    speedY = random(0, 10);
                    break;
                case SATELLITE:
                    speedY = random(0, 40);
                    break;
                case ASTEROID:
                    speedY = random(10, 50);
                    break;
                case FUELCAN:
                    speedY = 5;
                    speedX = 0;
            }
            //Set the speed
            velocity.setSpeed(speedX, speedY);
        }

        //Add the speed to the position so the object move:
        position.x += velocity.x * deltaTime;
        //Negative to move downwards
        position.y -= velocity.y * deltaTime;
    }

}
