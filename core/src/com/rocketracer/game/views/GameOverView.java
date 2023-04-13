package com.rocketracer.game.views;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rocketracer.game.ECS.Entities.SoundEntity;
import com.rocketracer.game.ECS.Systems.AudioSystem;
import com.rocketracer.game.controllers.MainController;

public class GameOverView implements Screen {


    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private Camera camera;
    private TextureAtlas atlas;
    protected Skin skin;
    private Table gameOverTable;
    Label.LabelStyle font;


    public GameOverView()
    {

        atlas = new TextureAtlas("CustomSkin.atlas");
        skin = new Skin(Gdx.files.internal("CustomSkin.json"), atlas);

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(1080/5, 2340/5, camera);

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();


        stage = new Stage(viewport, batch);
        gameOverTable = new Table();
        font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);


    }

    @Override
    public void show() {
        //Stage should control input:
        Gdx.input.setInputProcessor(stage);

        //Set table to fill stage
        gameOverTable.setFillParent(true);
        Label gameOverLabel = new Label("GAME OVER", font);
        Label playAgainLabel = new Label("Click to Play Again", font);

        gameOverTable.add(gameOverLabel).expandX();
        gameOverTable.row();
        gameOverTable.add(playAgainLabel).expandX().padTop(10f);


        //Add table to stage
        stage.addActor(gameOverTable);



    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()){
            ((Game)Gdx.app.getApplicationListener()).setScreen(new GameView());
        }
        Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();

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
        skin.dispose();
        atlas.dispose();
        batch.dispose();

    }
}
