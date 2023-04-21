package com.rocketracer.game.views;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rocketracer.game.GameEventListener;
import com.rocketracer.game.SharedData.LocalData;

import java.util.HashMap;
import java.util.Map;

public class GameOverView implements Screen, GameEventListener {
    private SpriteBatch batch;
    protected Stage stage;
    private Viewport viewport;
    private Camera camera;
    private TextureAtlas atlas;
    protected Skin skin;
    private Table gameOverTable, scoreTable;
    private Integer score;
    Label.LabelStyle font;

    private String docID;
    private Map<String, Long> mpScores = new HashMap<>();

    public GameOverView(Integer score, String docID)
    {
        // MP
        this.docID = docID;

        // General
        this.score = score;
        atlas = new TextureAtlas("CustomSkin.atlas");
        skin = new Skin(Gdx.files.internal("CustomSkin.json"), atlas);
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(1080/5, 2340/5, camera);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        stage = new Stage(viewport, batch);
        gameOverTable = new Table();
        font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
    }

    @Override
    public void show() {
        //Stage should control input:
        Gdx.input.setInputProcessor(stage);

        // Setting multiplayer handling and score table
        if (docID != null) {
            this.scoreTable = new Table(skin);
            scoreTable.setFillParent(true);
            stage.addActor(scoreTable);
            LocalData.sharedInstance.getFBIHandler()
                    .setGameListener(this);
        }

        //Set table to fill stage
        gameOverTable.setFillParent(true);

        // Label
        Label gameOverLabel = new Label("GAME OVER - Score: " + this.score.toString(), font);

        // Main page nav
        TextButton mainPageButton = new TextButton("Go to mainpage", skin);
        mainPageButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener())
                        .setScreen(LocalData.sharedInstance.getMainView());
            }
        });

        // Play again nav
        TextButton playAgainButton = new TextButton("Play new singleplayer-game", skin);
        playAgainButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new GameView(false, null));
            }
        });

        // Table handling
        gameOverTable.add(gameOverLabel).expandX();
        gameOverTable.row();
        gameOverTable.add(playAgainButton).expandX().padTop(10f);
        gameOverTable.row();
        gameOverTable.add(mainPageButton).expandX().padTop(10f);

        //Add table to stage
        stage.addActor(gameOverTable);
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

    @Override
    public String getDocID() {
        return docID;
    }

    @Override
    public void newSnapshotData(Map<String, Object> data) {
        mpScores.clear();
        try {
            Map<String, Object> playersMap = (Map<String, Object>) data.get("players");
            // Iterate through the playersMap and get player names
            for (Map.Entry<String, Object> entry : playersMap.entrySet()) {
                String name = entry.getKey();
                Long score = (Long) entry.getValue();
                mpScores.put(name, score);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (scoreTable != null) {
            scoreTable.clear();
            for (int i = 0; i < 10; i++) {
                scoreTable.row();
                scoreTable.add("  ");
            }
            for (Map.Entry<String, Long> entry : mpScores.entrySet()) {
                scoreTable.row();
                Long score = entry.getValue();
                String scoreStr = (score != -1) ? score.toString() : "Waiting...";
                scoreTable.add(entry.getKey() + ": " + scoreStr);
            }
        }
    }
}

