package com.rocketracer.game.views;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rocketracer.game.ECS.Components.ScoreComponent;
import com.rocketracer.game.controllers.GameController;

public class GameView implements Screen {

    // --- Attributes ---
    // Misc
    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private Camera camera;
    private TextureAtlas atlas;
    protected Skin skin;
    Label.LabelStyle font;

    private GameController gameController;
    float score;

    // Navigation
    /** For back button */
    //private Screen prevScreen;

    // Gameplay: will be moved to GameController


    // --- Constructor ---
    /**
     * Constructor.
     * @param prevScreen The screen to return to when the back button is pressed.
     */
    public GameView(/*Screen prevScreen*/) {
        atlas = new TextureAtlas("CustomSkin.atlas");
        skin = new Skin(Gdx.files.internal("CustomSkin.json"), atlas);
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        viewport = new FitViewport(1080/5, 2340/5, camera);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        stage = new Stage(viewport, batch);
        gameController = new GameController(batch);
        font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);


        //this.prevScreen = prevScreen;

    }

    @Override
    public void show() {
        //Stage should control input:
        Gdx.input.setInputProcessor(stage);
        ImmutableArray<Entity> scoreArray = gameController.getEngine().getEntitiesFor(Family.one(ScoreComponent.class).get());
        ScoreComponent scoreComponent = scoreArray.get(0).getComponent(ScoreComponent.class);
        score = scoreComponent.getScore();
        Label scoreLabel = new Label(Float.toString(score), font);
        scoreLabel.setPosition(130, 400);
        stage.addActor(scoreLabel);

    }

    @Override
    public void render(float delta) {
        //Clear the screen
        Gdx.gl.glClearColor(0, 0, .16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();

        gameController.getEngine().update(delta);

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        atlas.dispose();
        batch.dispose();
    }
}
