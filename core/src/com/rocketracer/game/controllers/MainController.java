package com.rocketracer.game.controllers;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MainController {

    public TextButton addButton(String name, Skin skin, Table mainTable){
        TextButton button = new TextButton(name, skin);
        mainTable.add(button).fillX().padBottom(10);
        mainTable.row();
        return button;
    }

    
}
