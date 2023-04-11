package com.rocketracer.game.ECS.Systems;

import static com.badlogic.gdx.math.MathUtils.random;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.rocketracer.game.ECS.Components.FuelComponent;
import com.rocketracer.game.ECS.Components.TypeComponent;

public class FuelSystem extends IteratingSystem {
    private ComponentMapper<FuelComponent> fMapper;
    private ComponentMapper<TypeComponent> tMapper;


    public FuelSystem() {
        super(Family.all(FuelComponent.class, TypeComponent.class).get());
        fMapper = ComponentMapper.getFor(FuelComponent.class);
        tMapper = ComponentMapper.getFor(TypeComponent.class);

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TypeComponent object = tMapper.get(entity);
        FuelComponent fuel = fMapper.get(entity);

        if(fuel.fuelLevel<= 0) {
            //TODO: make gameover
        }
        else{
            if (object == TypeComponent.OBSTACLE) {
            fuel.fuelLevel -=50;

        }
        else if (object == TypeComponent.POWERUP) {
            fuel.fuelLevel +=50;
        }
        else{
        fuel.fuelLevel -=1;
        }
        }

    }
}
