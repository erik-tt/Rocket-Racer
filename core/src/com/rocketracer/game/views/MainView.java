package com.rocketracer.game.views;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rocketracer.game.GameListener;
import com.rocketracer.game.ECS.Entities.SoundEntity;
import com.rocketracer.game.ECS.Systems.AudioSystem;
import com.rocketracer.game.SharedData.LocalData;
import com.rocketracer.game.controllers.MainController;


public class MainView implements Screen, GameListener {
    
    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private Camera camera;
    private TextureAtlas atlas;
    protected Skin skin;
    private Table mainTable;
    private MainController mainController;

    private SoundEntity soundEntity;


    AudioSystem audioSystem;


    
    public MainView()
    {

        soundEntity = new SoundEntity();
        atlas = new TextureAtlas("CustomSkin.atlas");
        skin = new Skin(Gdx.files.internal("CustomSkin.json"), atlas);
        
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(1080/5, 2340/5, camera);
        
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        audioSystem = new AudioSystem(soundEntity);
        
        stage = new Stage(viewport, batch);
        mainTable = new Table();
        mainController = new MainController();
        if (LocalData.sharedInstance.getFBIHandler() != null)
            LocalData.sharedInstance.getFBIHandler().setListener(this);
    }
    
    @Override
    public void show() {
        //Stage should control input:
        Gdx.input.setInputProcessor(stage);
        
        //Set table to fill stage
        mainTable.setFillParent(true);
        
        //Create buttons
        
        mainController.addButton("Play single-player game", skin, mainTable).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new GameView());
            }
        });
        
        mainController.addButton("Play multiplayer game", skin, mainTable).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Creating multiplayer game");
                System.out.println("PIN: " + LocalData.sharedInstance.getFBIHandler().createGame());
            }
        });
        
        mainController.addButton("Exit", skin, mainTable).addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        
        //Add table to stage
        stage.addActor(mainTable);
        stage.addActor(audioSystem.getSoundButton());
    }
    
    // Screen methods
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

    // GameListener methods
    @Override
    public void onGameLoaded(String docID, Integer pin) {
        System.out.println("Game loaded: " + docID + " - Pin: " + pin);
    }
    @Override
    public void onGameError() {
        System.out.println("Game error");
    }
    
}

