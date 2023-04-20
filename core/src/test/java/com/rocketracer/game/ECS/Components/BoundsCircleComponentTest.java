package com.rocketracer.game.ECS.Components;

import static junit.framework.TestCase.assertEquals;

import com.rocketracer.game.SharedData.GameConfig;

import org.junit.Before;
import org.junit.Test;

public class BoundsCircleComponentTest {
    private BoundsCircleComponent circleComponent;

    @Before
    public void setup() {
        circleComponent = new BoundsCircleComponent(5);
    }
    @Test
    public void testBound() {
        assertEquals(5/ GameConfig.PPM, circleComponent.bounds.radius);
    }
}
