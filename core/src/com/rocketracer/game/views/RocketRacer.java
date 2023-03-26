package com.rocketracer.game.views;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rocketracer.game.FirebaseInterface;

public class RocketRacer extends Game {
	SpriteBatch batch;
			Texture img;

	FirebaseInterface FBIHandler;

	public RocketRacer(FirebaseInterface FBIHandler) {
		FBIHandler = FBIHandler;
	}

	public BitmapFont font;

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
		batch.dispose();
		font.dispose();

	}
}
