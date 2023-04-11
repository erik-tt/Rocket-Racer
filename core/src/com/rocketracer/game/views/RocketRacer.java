package com.rocketracer.game.views;

import com.badlogic.gdx.Game;
import com.rocketracer.game.FirebaseInterface;

public class RocketRacer extends Game {
	FirebaseInterface FBIHandler;

	public RocketRacer(FirebaseInterface FBIHandler) {
		this.FBIHandler = FBIHandler;
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
