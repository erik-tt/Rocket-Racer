package com.rocketracer.game.ECS.Systems;



import static junit.framework.TestCase.assertEquals;

import com.rocketracer.game.ECS.Components.BackgroundComponent;
import com.rocketracer.game.ECS.Components.PositionComponent;
import com.rocketracer.game.ECS.Entities.TestEntity;

import org.junit.Before;
import org.junit.Test;

public class BackgroundSystemTest {
    TestEntity testEntity;
    BackgroundSystem backgroundSystem;

    @Before
    public void setup() {
        testEntity = new TestEntity();
        BackgroundComponent backgroundComponent = new BackgroundComponent(10, 10, 213);
        PositionComponent positionComponent = new PositionComponent(20, 5);

        testEntity.setBackgroundComponent(backgroundComponent);
        testEntity.setPositionComponent(positionComponent);

        backgroundSystem = new BackgroundSystem();

    }

    @Test
    public void testBackground() {
        //Test that the background speed stops at the top of the picture.
        backgroundSystem.processEntity(testEntity.getEntity(), 2);
        assertEquals(0, testEntity.getBackgroundComponent().speed);
    }
}
