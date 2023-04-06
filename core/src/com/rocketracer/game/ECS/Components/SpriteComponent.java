package com.rocketracer.game.ECS.Components;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteComponent implements Component {
    // --- Attributes ---
    public Sprite sprite;

    // --- Constructor ---
    public SpriteComponent(Sprite sprite) {
        this.sprite = sprite;
    }
}
