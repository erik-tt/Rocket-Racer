package com.rocketracer.game.views;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class RocketRacer extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	FuelCan fuelCan;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		fuelCan = new FuelCan();
		fuelCan.create();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		fuelCan.render();
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		fuelCan.dispose();
	}
}
