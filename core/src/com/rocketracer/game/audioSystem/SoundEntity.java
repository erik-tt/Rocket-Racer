package com.rocketracer.game.audioSystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class SoundEntity {
    // Attributes
    private TextureRegionDrawable sound;
    private TextureRegionDrawable mute;
    private boolean soundEnabled;


    // Constructor
    public SoundEntity(){
        soundEnabled = true;
    }

    //Get sound image when sound is on
    public TextureRegionDrawable getSound(){
        Texture texture = new Texture(Gdx.files.internal("sound.png"));
        sound = new TextureRegionDrawable(new TextureRegion(texture));
        return sound;
    }

    //Get mute image when sound is off
    public TextureRegionDrawable getMute(){
        Texture texture = new Texture(Gdx.files.internal("mute.png"));
        mute = new TextureRegionDrawable(new TextureRegion(texture));
        return mute;
    }

    // Methods

    //Set what image is going to be displayed
    public void setSoundEnabled(boolean soundEnabled){
        this.soundEnabled = soundEnabled;
    }

    public boolean getSoundEnabled(){
        return soundEnabled;
    }

}
