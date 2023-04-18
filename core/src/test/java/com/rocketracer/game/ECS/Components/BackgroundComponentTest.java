package com.rocketracer.game.ECS.Components;

import static junit.framework.TestCase.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BackgroundComponentTest {
    private BackgroundComponent backgroundComponent;

    @Before
    public void setup() {
        backgroundComponent = new BackgroundComponent(10, 20 ,2);
    }

    @Test
    public void testBackgroundPos() {
        assertEquals(10.0, backgroundComponent.y1);
        assertEquals(20.0, backgroundComponent.y2);
    }

    @Test
    public void testBackgroundSpeed() {
        assertEquals(2, backgroundComponent.speed);
    }

}
