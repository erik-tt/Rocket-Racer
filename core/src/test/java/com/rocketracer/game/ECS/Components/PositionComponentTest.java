package com.rocketracer.game.ECS.Components;

import static junit.framework.TestCase.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PositionComponentTest {
    private PositionComponent positionComponent;
    private float testX = 30;
    private float testY = 20;

    @Before
    public void setup() {
        positionComponent = new PositionComponent(30,20);
    }

    @Test
    public void testPositions() {
        assertEquals(testX, positionComponent.x);
        assertEquals(testY, positionComponent.y);
    }

    @Test
    public void testUpdateX() {
        positionComponent.updateX(10);
        testX = 40;
        assertEquals(testX, positionComponent.x);
    }

}
