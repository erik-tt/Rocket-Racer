package com.rocketracer.game.ECS.Systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rocketracer.game.ECS.Entities.SoundEntity;

public class AudioSystem {
    ImageButton soundButton;
    Sound sound = Gdx.audio.newSound(Gdx.files.internal("music.mp3"));

    public AudioSystem(final SoundEntity soundEntity){
        soundButton = new ImageButton(soundEntity.getSound(), soundEntity.getMute(), soundEntity.getMute());
        soundButton.setSize(25,25);
        soundButton.setPosition(0, 100);
        sound.play();
        sound.loop();
        soundButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(soundEntity.getSoundEnabled()){
                    sound.stop();
                }
                else{
                    sound.play();
                }
                soundEntity.setSoundEnabled(!soundEntity.getSoundEnabled());


            }
        });
    }

    public ImageButton getSoundButton(){
        return soundButton;
    }
}
