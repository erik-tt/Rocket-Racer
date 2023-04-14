package com.rocketracer.game.views;


import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rocketracer.game.ECS.Systems.ControlSystem;
import com.rocketracer.game.ECS.Systems.MovementSystem;
import com.rocketracer.game.ECS.Systems.RenderSystem;
import com.rocketracer.game.controllers.GameController;

public class HowToView implements Screen {
    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private Camera camera;
    private TextureAtlas atlas;
    protected Skin skin;

    //private HowToController HowToController;

    ImageButton backButton;

    public HowToView(){

        Texture texture = new Texture(Gdx.files.internal("backArrow.png"));
        TextureRegionDrawable backArrowDrawable = new TextureRegionDrawable(new TextureRegion(texture));

        backButton = new ImageButton(backArrowDrawable);

        atlas = new TextureAtlas("CustomSkin.atlas");
        skin = new Skin(Gdx.files.internal("CustomSkin.json"), atlas);
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        viewport = new FitViewport(1080/5, 2340/5, camera);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        stage = new Stage(viewport, batch);
        //howToController = new HowToController();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, .16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


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
