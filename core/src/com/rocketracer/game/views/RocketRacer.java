package com.rocketracer.game.views;

import com.badlogic.gdx.Game;
import com.rocketracer.game.FirebaseInterface;
import com.rocketracer.game.SharedData.LocalData;

public class RocketRacer extends Game {
	public RocketRacer(FirebaseInterface FBIHandler) {
		LocalData.sharedInstance.setFBIHandler(FBIHandler);
	}

	@Override
	public void create () {
		setScreen(new MainView());
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
	}
}
