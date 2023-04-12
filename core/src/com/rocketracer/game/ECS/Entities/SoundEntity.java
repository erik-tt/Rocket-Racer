package com.rocketracer.game.ECS.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class SoundEntity {

    private TextureRegionDrawable sound;
    private TextureRegionDrawable mute;

    private boolean soundEnabled;

    public SoundEntity(){
        soundEnabled = true;
    }

    public TextureRegionDrawable getSound(){
        Texture texture = new Texture(Gdx.files.internal("sound.png"));
        sound = new TextureRegionDrawable(new TextureRegion(texture));
        return sound;
    }

    public TextureRegionDrawable getMute(){
        Texture texture = new Texture(Gdx.files.internal("mute.png"));
        mute = new TextureRegionDrawable(new TextureRegion(texture));
        return mute;
    }

    public void setSoundEnabled(boolean soundEnabled){
        this.soundEnabled = soundEnabled;

    }

    public boolean getSoundEnabled(){
        return soundEnabled;
    }

}
