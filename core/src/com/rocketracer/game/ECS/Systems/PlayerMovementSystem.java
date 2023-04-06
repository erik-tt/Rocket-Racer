package com.rocketracer.game.ECS.Systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.rocketracer.game.ECS.Components.PositionComponent;
import com.rocketracer.game.ECS.Components.SpriteComponent;
import com.rocketracer.game.ECS.Components.VelocityComponent;
import com.rocketracer.game.controllers.GameController;

public class PlayerMovementSystem extends IteratingSystem {


    private ComponentMapper<VelocityComponent> vMapper;
    private ComponentMapper<SpriteComponent> sMapper;
    private GameController gameController;

    public PlayerMovementSystem(GameController controller) {
        super(Family.all(VelocityComponent.class).get());
        vMapper = ComponentMapper.getFor(VelocityComponent.class);
        sMapper = ComponentMapper.getFor(SpriteComponent.class);
        gameController = controller;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }
}
