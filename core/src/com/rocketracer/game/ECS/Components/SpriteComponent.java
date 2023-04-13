package com.rocketracer.game.ECS.Components;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteComponent implements Component {
    // --- Attributes ---
    public Sprite sprite;
    public Texture texture;

    // TODO: Support animations etc?

    // --- Constructor ---
    public SpriteComponent(Texture texture) {
        this.texture = texture;
        this.sprite = new Sprite(texture);
        this.sprite.setSize(texture.getWidth(), texture.getHeight());
    }
}
