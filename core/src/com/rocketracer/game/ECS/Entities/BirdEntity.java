package com.rocketracer.game.ECS.Entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.rocketracer.game.ECS.Components.BoundsCircleComponent;
import com.rocketracer.game.ECS.Components.CleanupComponent;
import com.rocketracer.game.ECS.Components.CollisionComponent;
import com.rocketracer.game.ECS.Components.SpecificTypeComponent;
import com.rocketracer.game.ECS.Components.PositionComponent;
import com.rocketracer.game.ECS.Components.SpriteComponent;
import com.rocketracer.game.ECS.Components.TypeComponent;
import com.rocketracer.game.ECS.Components.VelocityComponent;


public class BirdEntity implements IGameObject {
    private final TypeComponent typeComponent;
    private Entity entity;
    private SpriteComponent spriteComponent;
    private PositionComponent positionComponent;
    private VelocityComponent velocityComponent;
    private CleanupComponent cleanupComponent;
    private SpecificTypeComponent obstacleTypeComponent;

    private CollisionComponent collisionComponent;
    private BoundsCircleComponent boundsCircleComponent;

    private Texture bird = new Texture(Gdx.files.internal("flyingBird.png"));


    public BirdEntity(float x, float y){

        //Initiate entity with components
        this.entity = new Entity();
        this.spriteComponent = new SpriteComponent(bird);
        spriteComponent.sprite.setSize(200,200);
        this.positionComponent = new PositionComponent(x,y);
        this.velocityComponent = new VelocityComponent();
        this.typeComponent = TypeComponent.OBSTACLE;
        this.obstacleTypeComponent = SpecificTypeComponent.BIRD;
        this.cleanupComponent = new CleanupComponent();
        this.collisionComponent = new CollisionComponent();
        this.boundsCircleComponent = new BoundsCircleComponent(bird.getWidth()/6);

        entity.add(spriteComponent);
        entity.add(positionComponent);
        entity.add(velocityComponent);
        entity.add(typeComponent);
        entity.add(cleanupComponent);
        entity.add(obstacleTypeComponent);
        entity.add(collisionComponent);
        entity.add(boundsCircleComponent);


    }

    public Entity getEntity(){ return entity;}

    @Override
    public void build() {
        System.out.println("Build bird");
    }
}

