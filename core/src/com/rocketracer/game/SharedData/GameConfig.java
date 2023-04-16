package com.rocketracer.game.SharedData;

import com.badlogic.gdx.Gdx;

public class GameConfig {

    public static final float PPM = 32.0f; // sets the amount of pixels each metre of box2d objects contains

    // this gets the height and width of our camera frustrum based off the width and height of the screen and our pixel per meter ratio
    public static final float FRUSTUM_WIDTH = Gdx.graphics.getWidth()/PPM;
    public static final float FRUSTUM_HEIGHT = Gdx.graphics.getHeight()/PPM;

    public static final float PIXELS_TO_METRES = 1.0f / PPM; // get the ratio for converting pixels to metres

    public static final float OBSTACLE_SPAWN_TIME = 1f;
    public static int DIFFICULTY = 1;
    private GameConfig() {

    }
}
