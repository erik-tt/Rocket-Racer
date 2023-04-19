package com.rocketracer.game.ECS.Components;

import static junit.framework.TestCase.assertEquals;

import com.rocketracer.game.ECS.Entities.TestEntity;

import org.junit.Before;

public class TypeComponentTest {
    private TestEntity testEntity;

    @Before
    public void setup() {
        testEntity = new TestEntity();
        testEntity.setTypeComponent(TypeComponent.ROCKET);
    }

    @Before
    public void testSpecificType() {
        assertEquals(TypeComponent.ROCKET, testEntity.getTypeComponent());

        testEntity.setTypeComponent(TypeComponent.OBSTACLE);
        assertEquals(TypeComponent.OBSTACLE, testEntity.getTypeComponent());
    }
}

