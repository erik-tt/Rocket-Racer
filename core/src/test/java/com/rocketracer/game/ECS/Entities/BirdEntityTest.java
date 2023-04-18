package com.rocketracer.game.ECS.Entities;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BirdEntityTest {

    BirdEntity bird;

    @Before
    public void setup() {
        bird = new BirdEntity(0,0);
    }

    @Test
    public void testAdd() {
        assertEquals(42, 19+23);
    }
}
