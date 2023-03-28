package com.rocketracer.game.controllers;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rocketracer.game.SharedData.HighScoreList;

public class MainController {

    public TextButton addButton(String name, Skin skin, Table mainTable){
        TextButton button = new TextButton(name, skin);
        mainTable.add(button).fillX().padBottom(10);
        mainTable.row();

        // Add action to button - used to test highscorelist
        /*button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                HighScoreList.sharedInstance.addScore("Sindre", 100);
                HighScoreList.sharedInstance.printHighScoreList();
            }
        });*/ 

        return button;
    }

    
}
