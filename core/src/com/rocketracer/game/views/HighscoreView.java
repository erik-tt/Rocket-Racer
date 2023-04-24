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
import com.rocketracer.game.HighScoreListener;
import com.rocketracer.game.SharedData.GameConfig;
import com.rocketracer.game.SharedData.HighScoreList;
import com.rocketracer.game.SharedData.LocalData;

import java.util.HashMap;
import java.util.Map;

public class HighscoreView implements Screen, HighScoreListener {
    // -- Attributes --
    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private Camera camera;
    private TextureAtlas atlas;
    protected Skin skin;

    Label.LabelStyle font;
    Table highscoreTable;
    ImageButton backButton;

    Map<Integer, Map.Entry<String, Integer>> mpHighScores = new HashMap<>();

    // -- Construct --
    public HighscoreView(){
        HighScoreList.sharedInstance.reloadHighScores();
        atlas = new TextureAtlas("CustomSkin.atlas");
        skin = new Skin(Gdx.files.internal("CustomSkin.json"), atlas);




        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(1080/5, 2340/5, camera);

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        stage = new Stage(viewport, batch);
        highscoreTable = new Table();
        font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        HighScoreList.sharedInstance.listenMPHighScores(this);
    }

    // -- Screen Impl --
    @Override
    public void show() {
        //Stage should control input:
        Gdx.input.setInputProcessor(stage);

        //Set table to fill stage
        highscoreTable.setFillParent(true);


        Label highscoreLabel = new Label("MP HIGHSCORES", font);

        highscoreTable.add(highscoreLabel).expandX();
        Texture texture = new Texture(Gdx.files.internal("backArrow.png"));
        TextureRegionDrawable backArrowDrawable = new TextureRegionDrawable(new TextureRegion(texture));
        backButton = new ImageButton(backArrowDrawable);
        backButton.setSize(30,30);
        backButton.setPosition(0, 400);

        backButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener())
                        .setScreen(LocalData.sharedInstance.getMainView());

            }
        });

        //Add table to stage
        stage.addActor(backButton);
        stage.addActor(highscoreTable);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();

        // Async await workaround, displaying high scores when they are successfully loaded
        if (this.mpHighScores.size() > 0) {
            Integer pos = 1;
            for (Map.Entry<String, Integer> entry : this.mpHighScores.values()) {
                try {
                    if (entry == null) continue;
                    String key = entry.getKey();
                    Integer score = entry.getValue();
                    highscoreTable.row();
                    highscoreTable.add(new Label(pos.toString() + ". " + key + " : " + score.toString(), font));
                    pos++;
                } catch (Exception e) {
                    System.out.println("\n\n --- Caught error in HighScoreView when loading highscore entry --- \nTrace under: \n");
                    System.out.println(e.getStackTrace());
                }
            }
            this.mpHighScores.clear();
        }
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
        stage.dispose();
    }

    // -- HighScoreListener impl --
    /** Listening for high scores to load. */
    @Override
    public void onHighScoreFetched(Map<Integer, Map.Entry<String, Integer>> mpHighScores) {
        this.mpHighScores = mpHighScores;
    }
}
