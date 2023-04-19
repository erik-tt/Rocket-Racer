package com.rocketracer.game.ECS.Entities;


import com.badlogic.ashley.core.Entity;
import com.rocketracer.game.ECS.Components.CleanupComponent;
import com.rocketracer.game.ECS.Components.PositionComponent;
import com.rocketracer.game.ECS.Components.SpecificTypeComponent;
import com.rocketracer.game.ECS.Components.TypeComponent;
import com.rocketracer.game.ECS.Components.VelocityComponent;

public class TestEntity {
    /***
     * A class only used for testing purposes since it does not use LibGDX, we do not have to
     * use the headless backend for testing business logic. By doing this we can more
     * thoroughly test the systems that use entities.
     */

    private TypeComponent typeComponent;
    private Entity entity;
    private PositionComponent positionComponent;
    private VelocityComponent velocityComponent;
    private CleanupComponent cleanupComponent;
    private SpecificTypeComponent obstacleTypeComponent;

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

    //Getters
    public TypeComponent getTypeComponent() {
        return typeComponent;
    }

    public PositionComponent getPositionComponent() {
        return positionComponent;
    }

    public VelocityComponent getVelocityComponent() {
        return velocityComponent;
    }

    public CleanupComponent getCleanupComponent() {
        return cleanupComponent;
    }

    public SpecificTypeComponent getSpecificTypeComponent() {
        return obstacleTypeComponent;
    }

    public Entity getEntity() {
        return entity;
    }
}
