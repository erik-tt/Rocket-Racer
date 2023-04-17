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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rocketracer.game.ECS.Components.BoundsCircleComponent;
import com.rocketracer.game.ECS.Components.BoundsRectangleComponent;
import com.rocketracer.game.ECS.Components.ScoreComponent;
import com.rocketracer.game.SharedData.GameConfig;
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
    BitmapFont font;

    private GameController gameController;
    int score;


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
        font = new BitmapFont(Gdx.files.internal("default.fnt"),Gdx.files.internal("default.png"),false);
        font.getData().setScale(0.20f );

        //this.prevScreen = prevScreen;

    }

    @Override
    public void show() {
        //Stage should control input:
        Gdx.input.setInputProcessor(stage);
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


        batch.begin();
        font.draw(batch, Integer.toString(score), GameConfig.FRUSTUM_WIDTH-7, GameConfig.FRUSTUM_HEIGHT-5);
        batch.end();

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
