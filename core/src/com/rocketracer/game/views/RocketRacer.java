package com.rocketracer.game.views;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RocketRacer extends Game {
	SpriteBatch batch;
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
