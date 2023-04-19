package com.rocketracer.game.ECS.Components;

import static junit.framework.TestCase.assertNotNull;

import org.junit.Test;

public class CleanupComponentTest {

    @Test
    public void initCleanupTest() {
        assertNotNull(new CleanupComponent());
    }
}
