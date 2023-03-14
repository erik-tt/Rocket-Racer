package com.rocketracer.game.views;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class MainView implements Screen {

        private SpriteBatch batch;
        protected Stage stage;
        private Viewport viewport;
        private OrthographicCamera camera;
        private TextureAtlas atlas;
        protected Skin skin;
        private Table mainTable;

    public MainView()
        {
            atlas = new TextureAtlas("uiskin.atlas");
            skin = new Skin(Gdx.files.internal("uiskin.json"), atlas);

            batch = new SpriteBatch();
            camera = new OrthographicCamera();
            viewport = new ExtendViewport(400, 200, camera);


            camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
            camera.update();

            stage = new Stage(viewport, batch);
            mainTable = new Table();
        }


        @Override
        public void show() {
        //Stage should control input:
        Gdx.input.setInputProcessor(stage);

        //Set table to fill stage
        mainTable.setFillParent(true);

        //Create buttons

        addButton("Play single-player game").addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new GameView());
            }
        });

            addButton("Play multiplayer game").addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
               //TODO
                }
            });

      addButton("Exit").addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        //Add table to stage
        stage.addActor(mainTable);
    }

    //Add button to table
    private TextButton addButton(String name){
        TextButton button = new TextButton(name, skin);
        mainTable.add(button).fillX().padBottom(10);
        mainTable.row();
        return button;
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
    }



}

