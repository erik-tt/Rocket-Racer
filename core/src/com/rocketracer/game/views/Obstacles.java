package com.rocketracer.game.views;

import com.badlogic.gdx.graphics.Texture;

public interface Obstacles {
    int posY = 100;
    int posX = 100;
    Texture image = null;

    int getPosY();
    int getPosX();
    void updatePos();

}
