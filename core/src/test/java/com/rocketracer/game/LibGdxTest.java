package com.rocketracer.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessFiles;
import com.rocketracer.game.views.RocketRacer;

public class LibGdxTest {
    static {
        Gdx.files = new HeadlessFiles();

        new RocketRacer(new FBHandlerForTest());

    }
}
