package com.rocketracer.game.ECS.Components;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class FuelComponentTest {

    private FuelComponent fuelComponent;

    @Before
    public void setup() {
        fuelComponent = new FuelComponent(100);
    }

    @Test
    public void testInit() {
        assertNotNull(new CollisionComponent());
    }
    @Test
    public void testFuelLevel() {
        int fuelLevel = 100;
        assertEquals(fuelLevel, fuelComponent.fuelLevel);
    }
}
