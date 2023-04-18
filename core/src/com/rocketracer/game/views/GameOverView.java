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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rocketracer.game.SharedData.LocalData;

public class GameOverView implements Screen {
    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private Camera camera;
    private TextureAtlas atlas;
    protected Skin skin;
    private Table gameOverTable;
    private Integer score;
    Label.LabelStyle font;


    public GameOverView(Integer score) {
        this.score = score;
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
        Label gameOverLabel = new Label("GAME OVER - Score: " + this.score.toString(), font);
        TextButton playAgainButton = new TextButton("Click to Play Again", skin);
        TextButton mainPageButton = new TextButton("Go to mainpage", skin);
        playAgainButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new GameView());
            }
        });

        mainPageButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener())
                        .setScreen(LocalData.sharedInstance.getMainView());
            }
        });

        gameOverTable.add(gameOverLabel).expandX();
        gameOverTable.row();
        gameOverTable.add(playAgainButton).expandX().padTop(10f);
        gameOverTable.row();
        gameOverTable.add(mainPageButton).expandX().padTop(10f);

        //Add table to stage
        stage.addActor(gameOverTable);
    }

    @Override
    public void render(float delta) {
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
