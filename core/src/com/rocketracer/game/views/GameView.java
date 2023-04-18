package com.rocketracer.game.views;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rocketracer.game.ECS.Components.FuelComponent;
import com.rocketracer.game.ECS.Components.ScoreComponent;
import com.rocketracer.game.SharedData.GameConfig;
import com.rocketracer.game.SharedData.LocalData;
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
    ImageButton backButton;
    BitmapFont font;
    BitmapFont fontFuelLevel;

    private GameController gameController;
    private int score;

    private int fuelLevel;

    ShapeRenderer shape;


    // Navigation
    /** For back button */
    //private Screen prevScreen;

    // Gameplay: will be moved to GameController



    // --- Constructor ---
    public GameView(Boolean mpGame, String docID) {
        atlas = new TextureAtlas("CustomSkin.atlas");
        skin = new Skin(Gdx.files.internal("CustomSkin.json"), atlas);
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        viewport = new FitViewport(1080/5, 2340/5, camera);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        stage = new Stage(viewport, batch);
        Texture texture = new Texture(Gdx.files.internal("backArrow.png"));
        TextureRegionDrawable backArrowDrawable = new TextureRegionDrawable(new TextureRegion(texture));
        backButton = new ImageButton(backArrowDrawable);

        shape = new ShapeRenderer();

        gameController = new GameController(batch, mpGame, docID);
        font = skin.getFont("font");
        font.getData().setScale(0.20f);
        fontFuelLevel = new BitmapFont();
        fontFuelLevel.getData().setScale(0.20f);
    }

    @Override
    public void show() {
        //Stage should control input:
        Gdx.input.setInputProcessor(stage);
        backButton.setPosition(0, GameConfig.FRUSTUM_HEIGHT+130);
        backButton.setSize(30, 500);
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener())
                        .setScreen(LocalData.sharedInstance.getMainView());
            }
        });

        //Add table to stage
        stage.addActor(backButton);
    }

    @Override
    public void render(float delta) {
        //Clear the screen
        Gdx.gl.glClearColor(0, 0, .16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameController.getEngine().update(delta);

        ImmutableArray<Entity> scoreArray = gameController.getEngine().getEntitiesFor(Family.one(ScoreComponent.class).get());
        ScoreComponent scoreComponent = scoreArray.get(0).getComponent(ScoreComponent.class);
        score = scoreComponent.score;


        ImmutableArray<Entity> fuelArray = gameController.getEngine().getEntitiesFor(Family.one(FuelComponent.class).get());
        FuelComponent fuelComponent = fuelArray.get(0).getComponent(FuelComponent.class);
        fuelLevel = fuelComponent.fuelLevel;

        batch.begin();
        font.draw(batch, Integer.toString(score), GameConfig.FRUSTUM_WIDTH-7, GameConfig.FRUSTUM_HEIGHT-5);
        batch.end();
        shape.setProjectionMatrix(camera.projection);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.DARK_GRAY);
        shape.rect(GameConfig.FRUSTUM_WIDTH-50, -190, 120, 25);
        shape.end();


        batch.begin();
        fontFuelLevel.draw(batch, "Fuellevel: " + Integer.toString(fuelLevel), GameConfig.FRUSTUM_WIDTH-18, 9);
        batch.end();

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
        stage.dispose();
        skin.dispose();
        atlas.dispose();
        batch.dispose();
    }
}
