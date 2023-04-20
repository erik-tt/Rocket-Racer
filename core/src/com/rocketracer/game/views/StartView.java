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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class StartView implements Screen {


    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private Camera camera;
    private TextureAtlas atlas;
    protected Skin skin;
    private Table startTable;
    Label.LabelStyle font;
    private TextField textField;

    private Image image;


    public StartView()
    {

        atlas = new TextureAtlas("CustomSkin.atlas");
        skin = new Skin(Gdx.files.internal("CustomSkin.json"), atlas);
        Texture background = new Texture("frontpage.png");
        image = new Image(background);
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(1080/5, 2340/5, camera);

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        textField = new TextField("", skin);
        textField.setAlignment(1);


        stage = new Stage(viewport, batch);
        startTable = new Table();
        font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);


    }

    @Override
    public void show() {
        //Stage should control input:
        Gdx.input.setInputProcessor(stage);

        //Set table to fill stage
        startTable.setFillParent(true);

            startTable.row();
            startTable.add(new Label("Write your player-name", skin));
            startTable.row();
            startTable.add(textField);
            startTable.row();
        TextButton button = new TextButton("Go to main-page", skin);
        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!textField.getText().isEmpty()){
                    ((Game)Gdx.app.getApplicationListener()).setScreen(new MainView(textField.getText()));
                    Gdx.input.setOnscreenKeyboardVisible(false);
                }
                else if(startTable.getRows() < 4){
                    startTable.row();
                    startTable.add(new Label("You must fill in player-name ", skin)).fillX();
                }

            }});


        startTable.add(button);
        startTable.row();



        //Add table to stage
            stage.addActor(image);
        stage.addActor(startTable);



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
