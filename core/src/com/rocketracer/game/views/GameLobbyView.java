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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rocketracer.game.SharedData.LocalData;
import com.rocketracer.game.controllers.GameLobbyController;

public class GameLobbyView implements Screen {
    // -- Attributes --
    // Gdx
    private SpriteBatch batch;
    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Label gamePinLabel;
    private TextButton startGameButton;
    private Table playerTable;
    private Table mainTable;
    private Camera camera;
    private Viewport viewport;
    private Label.LabelStyle font;
    private ImageButton backButton;

    // Controller
    private GameLobbyController controller;

    public GameLobbyView(String docID, Integer pin) {
        System.out.println("Init lobby view");

        // Setting all params
        atlas = new TextureAtlas("CustomSkin.atlas");
        skin = new Skin(Gdx.files.internal("CustomSkin.json"), atlas);
        font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        this.batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(1080/5, 2340/5, camera);

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        stage = new Stage(viewport, batch);

        Texture texture = new Texture(Gdx.files.internal("backArrow.png"));
        TextureRegionDrawable backArrowDrawable = new TextureRegionDrawable(new TextureRegion(texture));
        backButton = new ImageButton(backArrowDrawable);

        gamePinLabel = new Label("Game PIN: " + pin, skin);
        startGameButton = new TextButton("Start Game", skin);

        startGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle start game button click
                System.out.println("Start Game button clicked");
                controller.startGame();
            }
        });

        this.controller = new GameLobbyController(this, docID, pin);
        mainTable = new Table();
        playerTable = new Table();
    }

    // -- Screen impl --
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        mainTable.setFillParent(true);
        playerTable.setFillParent(true);

        backButton.setPosition(0, 120);
        backButton.setSize(30, 550);
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                LocalData.sharedInstance.getFBIHandler().removeGameListener();
                ((Game)Gdx.app.getApplicationListener())
                        .setScreen(LocalData.sharedInstance.getMainView());
            }
        });

        mainTable.add(gamePinLabel).expandX().padBottom(10);
        mainTable.row();
        mainTable.add(startGameButton).expandX().padBottom(10);
        mainTable.row();
        mainTable.add(playerTable);
        mainTable.setBackground(skin.getDrawable("button-over-c"));

        stage.addActor(mainTable);
        stage.addActor(backButton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
    }

    // -- Updates from controller --
    public void reloadTable() {
        playerTable.clear();
        for (String player : controller.getPlayersList()) {
            playerTable.row();
            playerTable.add(new Label(player, font));
        }
    }
}
