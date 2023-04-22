package com.rocketracer.game.ECS.Entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.rocketracer.game.ECS.Components.BoundsRectangleComponent;
import com.rocketracer.game.ECS.Components.CleanupComponent;
import com.rocketracer.game.ECS.Components.CollisionComponent;
import com.rocketracer.game.ECS.Components.SpecificTypeComponent;
import com.rocketracer.game.ECS.Components.PositionComponent;
import com.rocketracer.game.ECS.Components.SpriteComponent;
import com.rocketracer.game.ECS.Components.TypeComponent;
import com.rocketracer.game.ECS.Components.VelocityComponent;

public class PlaneEntity implements IGameObject {

    //Attributes
    private Entity entity;
    private SpriteComponent spriteComponent;
    private PositionComponent positionComponent;
    private VelocityComponent velocityComponent;
    private CleanupComponent cleanupComponent;
    private TypeComponent typeComponent;
    private SpecificTypeComponent obstacleTypeComponent;
    private CollisionComponent collisionComponent;
    private BoundsRectangleComponent boundsRectangleComponent;
    private Texture plane = new Texture(Gdx.files.internal("plane.png"));

    // Constructor
    public PlaneEntity(float x, float y){

        //Initiate entity with components
        this.entity = new Entity();
        this.spriteComponent = new SpriteComponent(plane);
        this.positionComponent = new PositionComponent(x,y);
        this.velocityComponent = new VelocityComponent();
        this.cleanupComponent = new CleanupComponent();
        this.collisionComponent = new CollisionComponent();
        spriteComponent.sprite.setSize(Math.round(spriteComponent.sprite.getWidth()*0.8),Math.round(spriteComponent.sprite.getHeight()*0.8) );
        this.boundsRectangleComponent = new BoundsRectangleComponent(Math.round(spriteComponent.sprite.getWidth()*2/3), Math.round(spriteComponent.sprite.getHeight()*2/3));

        //Define types:
        this.typeComponent = TypeComponent.OBSTACLE;
        this.obstacleTypeComponent = SpecificTypeComponent.PLANE;

        //Add components to entity
        entity.add(spriteComponent);
        entity.add(positionComponent);
        entity.add(velocityComponent);
        entity.add(cleanupComponent);
        entity.add(typeComponent);
        entity.add((obstacleTypeComponent));
        entity.add(boundsRectangleComponent);
        entity.add(collisionComponent);
    }

    public Entity getEntity(){ return entity;}

    @Override
    public void build() {
        System.out.println("Build Plane");
    }
}
