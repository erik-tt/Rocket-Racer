package com.rocketracer.game.ECS.Systems;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rocketracer.game.ECS.Entities.SoundEntity;

public class AudioSystem {
    ImageButton soundButton;


    Music music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));

    public AudioSystem(final SoundEntity soundEntity){
        soundButton = new ImageButton(soundEntity.getSound(), soundEntity.getMute(), soundEntity.getMute());
        soundButton.setSize(25,25);
        soundButton.setPosition(0, 0);
        music.play();
        music.setLooping(true);

        soundButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(music.isPlaying()){
                    music.stop();
                }
                else{
                    music.play();


                }
                soundEntity.setSoundEnabled(!soundEntity.getSoundEnabled());
            }
        });
    }

    public ImageButton getSoundButton(){
        return soundButton;
    }
}
