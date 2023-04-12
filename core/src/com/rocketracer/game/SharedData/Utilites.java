package com.rocketracer.game.SharedData;

import com.badlogic.gdx.graphics.Camera;

/**
 * A class for different utilites.
 * The first intended use is sharing the camera instance.
 */
public class Utilites {

    private static Camera camera;

    public Utilites(Camera camera) {
        this.camera = camera;
    }

    public static Camera getCamera() {
        return camera;
    }
}
