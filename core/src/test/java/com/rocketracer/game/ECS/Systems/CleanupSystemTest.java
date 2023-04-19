package com.rocketracer.game.ECS.Systems;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
import com.rocketracer.game.ECS.Components.CleanupComponent;
import com.rocketracer.game.ECS.Components.CollisionComponent;
import com.rocketracer.game.ECS.Components.PositionComponent;
import com.rocketracer.game.ECS.Entities.TestEntity;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import jdk.nashorn.internal.ir.annotations.Immutable;

public class CleanupSystemTest {

    private CleanupSystem cleanupSystem;
    private Engine testEngine;
    private TestEntity entity;

    @Before
    public void setup() {
        testEngine = new Engine();
        entity = new TestEntity();
        CleanupComponent cleanupComponent = new CleanupComponent();
        CollisionComponent collisionComponent = new CollisionComponent();
        PositionComponent positionComponent = new PositionComponent(10,10);

        entity.setCleanupComponent(cleanupComponent);
        entity.setCollisionComponent(collisionComponent);
        entity.setPositionComponent(positionComponent);

        testEngine.addEntity(entity.getEntity());
        cleanupSystem = new CleanupSystem(testEngine);
        testEngine.addSystem(cleanupSystem);
    }

    @Test
    public void testCleanUp() {
        ImmutableArray<Entity> list = testEngine.getEntities();
        assertTrue(list.contains(entity.getEntity(), false));

        //Should still be in the engine
        cleanupSystem.processEntity(list.get(0), 0);
        assertTrue(list.contains(entity.getEntity(), false));

        PositionComponent positionComponent = new PositionComponent(10,-1);
        entity.setPositionComponent(positionComponent);

        //Should have been removed due to position being lower than 0
        cleanupSystem.processEntity(list.get(0), 0);
        assertFalse(list.contains(entity.getEntity(), false));


        positionComponent.y = 10;
        CollisionComponent collisionComponent = new CollisionComponent();
        collisionComponent.hit = true;
        entity.setCollisionComponent(collisionComponent);

        //Should have been removed due to collision hit.
        testEngine.addEntity(entity.getEntity());
        cleanupSystem.processEntity(list.get(0), 0);
        assertFalse(list.contains(entity.getEntity(), false));
    }
}
