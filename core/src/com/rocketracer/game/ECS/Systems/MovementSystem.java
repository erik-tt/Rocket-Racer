package com.rocketracer.game.ECS.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.rocketracer.game.ECS.Components.TypeComponent;
import com.rocketracer.game.ECS.Components.VelocityComponent;
import static com.badlogic.gdx.math.MathUtils.random;

public class MovementSystem extends IteratingSystem {

    private ComponentMapper<VelocityComponent> vMapper;
    private ComponentMapper<TypeComponent> tMapper;

    public MovementSystem() {
        super(Family.all(VelocityComponent.class, TypeComponent.class).get());
        vMapper = ComponentMapper.getFor(VelocityComponent.class);
        tMapper = ComponentMapper.getFor(TypeComponent.class);

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TypeComponent object = tMapper.get(entity);
        VelocityComponent velocity = vMapper.get(entity);

        //TODO
        //Create a more complex movement system based on type and gameplay difficulty

        if (object == TypeComponent.OBSTACLE) {
            //Just a random number from 1-10 for the obstical speed in y direction.
            int speedY = - random.nextInt(10);
            velocity.y += speedY;
        }

    }
}
