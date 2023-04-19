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
        float y1 = 10;
        float y2 = 20;
        assertEquals(y1, backgroundComponent.y1);
        assertEquals(y2, backgroundComponent.y2);
    }

    @Test
    public void testBackgroundSpeed() {
        assertEquals(2, backgroundComponent.speed);
    }

}
