package com.rocketracer.game.ECS.Components;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

import com.rocketracer.game.ECS.Entities.TestEntity;

import org.junit.Before;
import org.junit.Test;

public class CollisionComponentTest {

    private CollisionComponent collisionComponent;

    @Before
    public void setup() {
        collisionComponent = new CollisionComponent();
    }

    @Test
    public void testInit() {
        assertNotNull(new CollisionComponent());
    }

    @Test
    public void testCollisionEntity() {
        TestEntity testEntity = new TestEntity();
        collisionComponent.collidedEntity = testEntity.getEntity();
        assertEquals(testEntity.getEntity(), collisionComponent.collidedEntity);
    }
}
