package com.rocketracer.game;

import com.badlogic.gdx.Game;
import com.rocketracer.game.SharedData.LocalData;
import com.rocketracer.game.views.StartView;

/**
** Initiates the application
 **/
public class RocketRacer extends Game {
    public RocketRacer(FirebaseInterface FBIHandler) {
        LocalData.sharedInstance.setFBIHandler(FBIHandler);
    }

    //Sets screen to StartView
    @Override
    public void create () {
        setScreen(new StartView());
    }

    @Override
    public void render () {
        super.render();
    }

    @Override
    public void dispose () {
    }
}
