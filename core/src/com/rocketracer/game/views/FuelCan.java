package com.rocketracer.game.views;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FuelCan {

    SpriteBatch spriteBatch;
    Texture fuelCan = new Texture(Gdx.files.internal("fuelcan.png"));
    Sprite yourSprite;

    protected void create () {
        spriteBatch = new SpriteBatch();
        yourSprite = new Sprite(fuelCan);
        yourSprite.setSize(fuelCan.getWidth()-120, fuelCan.getHeight()-120);
    }

    protected void render () {

        yourSprite.setX(50);
        yourSprite.setY(50);
        Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        yourSprite.draw(spriteBatch);
        spriteBatch.end();
    }

    protected void dispose () {
        fuelCan.dispose();
        spriteBatch.dispose();
    }

}