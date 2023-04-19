package com.rocketracer.game.ECS.Components;

import static junit.framework.TestCase.assertEquals;

import com.rocketracer.game.ECS.Entities.TestEntity;

import org.junit.Before;

public class SpecificTypeComponentTest {
    private TestEntity testEntity;

    @Before
    public void setup() {
        testEntity = new TestEntity();
        testEntity.setSpecificTypeComponent(SpecificTypeComponent.BIRD);
    }

    @Before
    public void testSpecificType() {
        assertEquals(SpecificTypeComponent.BIRD, testEntity.getSpecificTypeComponent());

        testEntity.setSpecificTypeComponent(SpecificTypeComponent.ASTEROID);
        assertEquals(SpecificTypeComponent.ASTEROID, testEntity.getSpecificTypeComponent());
    }
}
