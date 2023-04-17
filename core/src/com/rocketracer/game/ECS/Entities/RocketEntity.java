package com.rocketracer.game.ECS.Entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.rocketracer.game.ECS.Components.*;
import com.rocketracer.game.SharedData.GameConfig;

public class RocketEntity {
    // --- Attributes ---
    private Entity entity;
    private SpriteComponent spriteComponent;
    private PositionComponent positionComponent;
    private FuelComponent fuelComponent;
    private ScoreComponent scoreComponent;
    private Texture rocket = new Texture(Gdx.files.internal("Rocket1.png"));
    private CollisionComponent collisionComponent;
    private BoundsRectangleComponent boundsRectangleComponent;
    private TypeComponent typeComponent;

    // --- Constructor ---
    public RocketEntity() {
        this.entity = new Entity();
        this.spriteComponent = new SpriteComponent(rocket);
        this.positionComponent = new PositionComponent(12, GameConfig.FRUSTUM_HEIGHT/6);
        this.fuelComponent = new FuelComponent(100);
        this.typeComponent = TypeComponent.ROCKET;
        this.collisionComponent = new CollisionComponent();
        this.boundsRectangleComponent = new BoundsRectangleComponent(rocket.getWidth()/7, rocket.getHeight()*2/3);

        this.scoreComponent = new ScoreComponent(0);


        entity.add(spriteComponent);
        entity.add(positionComponent);
        entity.add(fuelComponent);
        entity.add(typeComponent);


        entity.add(collisionComponent);
        entity.add(boundsRectangleComponent);

        entity.add(scoreComponent);

    }

    // --- Methods ---
    public Entity getEntity() {
        return entity;
    }
}

