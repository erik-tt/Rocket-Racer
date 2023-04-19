package com.rocketracer.game.ECS.Components;

import static junit.framework.TestCase.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class VelocityComponentTest {
    private VelocityComponent velocityComponent;

    @Before
    public void setup(){
        velocityComponent = new VelocityComponent();
    }

    @Test
    public void testSetSpeed() {
        float x = 5;
        float y = 2;

        assertEquals(false, velocityComponent.isSpeedSet());

        velocityComponent.setSpeed(x,y);
        assertEquals(x, velocityComponent.x);
        assertEquals(y, velocityComponent.y);
        assertEquals(true, velocityComponent.isSpeedSet());
    }
}
