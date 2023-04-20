package com.rocketracer.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.rocketracer.game.SharedData.HighScoreList;
import com.rocketracer.game.SharedData.LocalData;

public class MainController {
    // -- Attributes --

    // -- Methods --

    /**
     * Add button to table.
     * @param name
     * @param skin
     * @param mainTable
     * @return the button instance
     */
    public TextButton addButton(String name, Skin skin, Table mainTable){
        TextButton button = new TextButton(name, skin);
        mainTable.add(button).fillX().padBottom(10);
        mainTable.row();

        return button;
    }

    /**
     * Shows a join game dialog that prompts the user for a pin.
     * @param skin
     * @param stage
     */
    public void showJoinGameDialog(Skin skin, Stage stage) {
        final Dialog joinGameDialog = new Dialog("", skin);
        final TextField pinTextField = new TextField("", skin);
        final Label title = new Label("Join Game",skin);

        TextButton cancelButton = new TextButton("Cancel", skin);
        TextButton joinButton = new TextButton("Join", skin);

        cancelButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                joinGameDialog.hide();
                Gdx.input.setOnscreenKeyboardVisible(false);
            }
        });

        joinButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String pin = pinTextField.getText();
                System.out.println("Join Game with PIN: " + pin);
                try {
                    LocalData.sharedInstance.getFBIHandler().joinGame(Integer.valueOf(pin), LocalData.sharedInstance.playerName);
                } catch (Exception e) {
                    System.out.println("Bad input for game pin.");
                }
                joinGameDialog.hide();
                Gdx.input.setOnscreenKeyboardVisible(false);
            }
        });
        joinGameDialog.getContentTable().add(title).pad(10);
        joinGameDialog.getContentTable().row();
        joinGameDialog.getContentTable().add(pinTextField).width(100).pad(10);
        joinGameDialog.getButtonTable().add(cancelButton).pad(8);
        joinGameDialog.getButtonTable().add(joinButton).width(cancelButton.getWidth()).pad(8);
        joinGameDialog.setBackground(skin.getDrawable("button-over-c"));
        joinGameDialog.setHeight(joinGameDialog.getHeight()*1.3f);
        joinGameDialog.show(stage)
                .setPosition(stage.getWidth() / 2 - joinGameDialog.getWidth() / 2,
                             stage.getHeight() / 2 - joinGameDialog.getHeight() / 2);
    }

    public void showDialog(Skin skin, Stage stage, String title, String content) {
        final Dialog dialog = new Dialog(title, skin);
        TextButton okButton = new TextButton("Done", skin);
        Label message = new Label(content, skin);

        okButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dialog.hide();
            }
        });

        dialog.getContentTable().add(message).pad(10);
        dialog.getButtonTable().add(okButton).pad(10);
        dialog.setBackground(skin.getDrawable("button-over-c"));
        dialog.setHeight(dialog.getHeight()*1.3f);
        dialog.show(stage)
                .setPosition(stage.getWidth() / 2 - dialog.getWidth() / 2,
                        stage.getHeight() / 2 - dialog.getHeight() / 2);
    }

    
}
