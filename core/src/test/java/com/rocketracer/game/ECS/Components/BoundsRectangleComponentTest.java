package com.rocketracer.game.ECS.Components;

import static junit.framework.TestCase.assertEquals;

import com.rocketracer.game.SharedData.GameConfig;

import org.junit.Before;
import org.junit.Test;

public class BoundsRectangleComponentTest {
    private BoundsRectangleComponent rectangleComponent;

    @Before
    public void setup() {
        rectangleComponent = new BoundsRectangleComponent(5, 10);
    }
    @Test
    public void testBound() {
        assertEquals(5/ GameConfig.PPM, rectangleComponent.bounds.width);
        assertEquals(10/ GameConfig.PPM, rectangleComponent.bounds.height);
    }
}
