package com.rocketracer.game;


import com.badlogic.ashley.core.Entity;
import com.rocketracer.game.ECS.Components.BackgroundComponent;
import com.rocketracer.game.ECS.Components.BoundsCircleComponent;
import com.rocketracer.game.ECS.Components.BoundsRectangleComponent;
import com.rocketracer.game.ECS.Components.CleanupComponent;
import com.rocketracer.game.ECS.Components.CollisionComponent;
import com.rocketracer.game.ECS.Components.FuelComponent;
import com.rocketracer.game.ECS.Components.PositionComponent;
import com.rocketracer.game.ECS.Components.ScoreComponent;
import com.rocketracer.game.ECS.Components.SpecificTypeComponent;
import com.rocketracer.game.ECS.Components.TypeComponent;
import com.rocketracer.game.ECS.Components.VelocityComponent;


public class TestEntity {
    /***
     * A class only used for testing purposes since it does not use LibGDX, we do not have to
     * use the headless backend for testing business logic. By doing this we can more
     * thoroughly test the systems that use entities and do not rely on libGDX.
     */

    private TypeComponent typeComponent;
    private Entity entity;
    private BackgroundComponent backgroundComponent;
    private PositionComponent positionComponent;
    private VelocityComponent velocityComponent;
    private CollisionComponent collisionComponent;
    private CleanupComponent cleanupComponent;
    private SpecificTypeComponent obstacleTypeComponent;
    private BoundsCircleComponent boundsCircleComponent;
    private BoundsRectangleComponent boundsRectangleComponent;
    private ScoreComponent scoreComponent;

    private FuelComponent fuelComponent;

    public TestEntity() {
        this.entity = new Entity();
    }

    //Setters
    public void setTypeComponent(TypeComponent typeComponent) {
        this.typeComponent = typeComponent;
        entity.add(typeComponent);
    }

    public void setPositionComponent(PositionComponent positionComponent) {
        this.positionComponent = positionComponent;
        entity.add(positionComponent);
    }

    public void setVelocityComponent(VelocityComponent velocityComponent) {
        this.velocityComponent = velocityComponent;
        entity.add(velocityComponent);
    }

    public void setSpecificTypeComponent(SpecificTypeComponent obstacleTypeComponent) {
        this.obstacleTypeComponent = obstacleTypeComponent;
        entity.add(obstacleTypeComponent);
    }

    public void setCleanupComponent(CleanupComponent cleanupComponent) {
        this.cleanupComponent = cleanupComponent;
        entity.add(cleanupComponent);
    }
    public void setCollisionComponent(CollisionComponent collisionComponent) {
        this.collisionComponent = collisionComponent;
        entity.add(collisionComponent);
    }
    public void setFuelComponent(FuelComponent fuelComponent) {
        this.fuelComponent = fuelComponent;
        entity.add(fuelComponent);
    }
    public void setBackgroundComponent(BackgroundComponent backgroundComponent) {
        this.backgroundComponent = backgroundComponent;
        entity.add(backgroundComponent);
    }
    public void setBoundsCircleComponent(BoundsCircleComponent boundsCircleComponent) {
        this.boundsCircleComponent = boundsCircleComponent;
        entity.add(boundsCircleComponent);
    }
    public void setBoundsRectangleComponent(BoundsRectangleComponent boundsRectangleComponent) {
        this.boundsRectangleComponent = boundsRectangleComponent;
        entity.add(boundsRectangleComponent);
    }
    public void setScoreComponent(ScoreComponent scoreComponent) {
        this.scoreComponent = scoreComponent;
        entity.add(scoreComponent);
    }

    //Getters
    public TypeComponent getTypeComponent() {
        return typeComponent;
    }
    public BoundsRectangleComponent getBoundsRectangleComponent() { return boundsRectangleComponent; }
    public BoundsCircleComponent getBoundsCircleComponent() {return boundsCircleComponent; }

    public PositionComponent getPositionComponent() {
        return positionComponent;
    }

    public VelocityComponent getVelocityComponent() {
        return velocityComponent;
    }

    public CollisionComponent getCollisionComponent() {
        return collisionComponent;
    }

    public CleanupComponent getCleanupComponent() {
        return cleanupComponent;
    }
    public BackgroundComponent getBackgroundComponent() { return backgroundComponent; }

    public SpecificTypeComponent getSpecificTypeComponent() {
        return obstacleTypeComponent;
    }
    public FuelComponent getFuelComponent() { return fuelComponent; }
    public ScoreComponent getScoreComponent() { return scoreComponent; }

    public Entity getEntity() {
        return entity;
    }
}
