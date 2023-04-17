package com.rocketracer.game.views;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class HighscoreView implements Screen {
    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private Camera camera;
    private TextureAtlas atlas;
    protected Skin skin;

    Label.LabelStyle font;

    Table highscoreTable;

    ImageButton backButton;



    public HighscoreView(){
        atlas = new TextureAtlas("CustomSkin.atlas");
        skin = new Skin(Gdx.files.internal("CustomSkin.json"), atlas);

        Texture texture = new Texture(Gdx.files.internal("backArrow.png"));
        TextureRegionDrawable backArrowDrawable = new TextureRegionDrawable(new TextureRegion(texture));

        backButton = new ImageButton(backArrowDrawable);

        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(1080/5, 2340/5, camera);

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();


        stage = new Stage(viewport, batch);
        highscoreTable = new Table();
        font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

    }


    @Override
    public void show() {
        //Stage should control input:
        Gdx.input.setInputProcessor(stage);

        //Set table to fill stage
        highscoreTable.setFillParent(true);
        Label highscoreLabel = new Label("HIGHSCORES", font);


        highscoreTable.add(highscoreLabel).expandX();

        backButton.setPosition(0, 100);
        backButton.setSize(30, 550);
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MainView());
            }
        });



        //Add table to stage
        stage.addActor(backButton);
        stage.addActor(highscoreTable);
;



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
