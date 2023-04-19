package com.rocketracer.game.ECS.Components;

import static junit.framework.TestCase.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class CollisionComponent {

    private CollisionComponent collisionComponent;

    @Before
    public void setup() {
        collisionComponent = new CollisionComponent();
    }

    @Test
    public void testInit() {
        assertNotNull(new CollisionComponent());
    }
}
