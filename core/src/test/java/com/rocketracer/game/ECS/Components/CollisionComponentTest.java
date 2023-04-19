package com.rocketracer.game.ECS.Components;

import static junit.framework.TestCase.assertNotNull;

import com.rocketracer.game.ECS.Entities.TestEntity;

import org.junit.Before;
import org.junit.Test;

public class CollisionComponent {

    private CollisionComponent collisionComponent = new CollisionComponent();

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

    }
}
