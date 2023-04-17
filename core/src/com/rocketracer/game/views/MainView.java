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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rocketracer.game.GameJoinListener;
import com.rocketracer.game.ECS.Entities.SoundEntity;
import com.rocketracer.game.ECS.Systems.AudioSystem;
import com.rocketracer.game.SharedData.LocalData;
import com.rocketracer.game.controllers.MainController;


public class MainView implements Screen, GameJoinListener {

    // -- Attributes --
    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private Camera camera;
    private TextureAtlas atlas;
    protected Skin skin;
    private Table mainTable;
    private MainController mainController;

    private SoundEntity soundEntity;
    private Image image;
    private TextField textField;

    AudioSystem audioSystem;

    private boolean hasInit = false;

    // -- Construct --
    public MainView()
    {
        LocalData.sharedInstance.setMainView(this);
        soundEntity = new SoundEntity();
        Texture background = new Texture("frontpage.png");
        image = new Image(background);
        atlas = new TextureAtlas("CustomSkin.atlas");
        skin = new Skin(Gdx.files.internal("CustomSkin.json"), atlas);
        textField = new TextField("", skin);
        textField.setAlignment(1);

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

    // -- Screen Impl --
    @Override
    public void show() {
        //Stage should control input:
        Gdx.input.setInputProcessor(stage);
        
        //Set table to fill stage
        mainTable.setFillParent(true);
        
        //Create buttons
        
        if (!hasInit) {
            mainTable.row();
            mainTable.add(new Label("Player-tag", skin));
            mainTable.row();
            mainTable.add(textField);
            mainTable.row();

            mainController.addButton("Play single-player game", skin, mainTable).addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    ((Game)Gdx.app.getApplicationListener()).setScreen(new GameView());
                }
            });

            mainController.addButton("Create multiplayer game", skin, mainTable).addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (textField.getText().length() == 0) return;
                    LocalData.sharedInstance.playerName = textField.getText();
                    System.out.println("Creating multiplayer game");
                    System.out.println("PIN: " + LocalData.sharedInstance.getFBIHandler().createGame());
                }
            });
            mainController.addButton("Join multiplayer game", skin, mainTable).addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (textField.getText().length() == 0) return;
                    LocalData.sharedInstance.playerName = textField.getText();
                    mainController.showJoinGameDialog(skin, stage);
                }
            });
            mainController.addButton("High-scores", skin, mainTable).addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    ((Game)Gdx.app.getApplicationListener()).setScreen(new HighscoreView());
                }
            });
            mainController.addButton("How to play", skin, mainTable).addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    ((Game)Gdx.app.getApplicationListener()).setScreen(new HowToView());
                }
    
            });
            /*mainController.addButton("Exit", skin, mainTable).addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Gdx.app.exit();
                }
            });*/
            hasInit = true;
        }
        
        //Add table to stage
        stage.addActor(image);
        stage.addActor(mainTable);
        stage.addActor(audioSystem.getSoundButton());
        hasInit = true;
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        stage.act(delta);
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

    // -- GameListener Impl --
    @Override
    public void onGameLoaded(final String docID, final Integer pin) {
        System.out.println("Game loaded: " + docID + " - Pin: " + pin);
        // Ensure setting screen is ran on rendering thread
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new GameLobbyView(docID, pin));
            }
        });
    }

    @Override
    public void onGameError() {
        System.out.println("Game error");
        mainController.showDialog(skin, stage, "Error: Online Game", "Error joining online game. Please try again.");
    }
    
}

