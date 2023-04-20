package com.rocketracer.game.ECS.Components;

import static junit.framework.TestCase.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ScoreComponentTest {

    private ScoreComponent scoreComponent;

    @Before
    public void setup() {
        scoreComponent = new ScoreComponent(100);

    }
    @Test
    public void testScore() {
        assertEquals(100, scoreComponent.getScore());
        assertEquals(false, scoreComponent.gameOver);
    }
}
