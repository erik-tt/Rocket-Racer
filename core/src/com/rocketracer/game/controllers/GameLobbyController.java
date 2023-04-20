package com.rocketracer.game.controllers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.rocketracer.game.GameEventListener;
import com.rocketracer.game.SharedData.LocalData;
import com.rocketracer.game.views.GameLobbyView;
import com.rocketracer.game.views.GameView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameLobbyController implements GameEventListener {
    // -- Attributes --
    private GameLobbyView lobby;

    // misc
    private List<String> playersList;

    // Game data
    final private String docID;
    private Integer gamePin;

    // -- Construct --
    public GameLobbyController(GameLobbyView lobby,
                               String docID, Integer pin) {
        System.out.println("Init GameLobbyController");
        this.lobby = lobby;
        this.playersList = new ArrayList<>();
        this.gamePin = pin;
        this.docID = docID;
        initGameListener();
    }

    private void initGameListener() {
        LocalData.sharedInstance.getFBIHandler()
                .setGameListener(this);
    }

    // -- Methods --
    public List<String> getPlayersList() { return this.playersList; }
    public void addPlayer(String playerName) {
        playersList.add(playerName);
        lobby.reloadTable();
    }
    public void startGame() {
        if (this.docID == null) return;
        HashMap<String, Object> gameState = new HashMap<>();
        gameState.put("state", 1);
        gameState.put("pin", -1); // Ensuring pin won't be reallocated
        LocalData.sharedInstance.getFBIHandler()
                .writeData(gameState, "games/" + this.docID);
    }

    @Override
    public String getDocID() {
        return this.docID;
    }

    @Override
    public void newSnapshotData(Map<String, Object> data) {
        // Players list
        playersList.clear();
        try {
            Map<String, Object> playersMap = (Map<String, Object>) data.get("players");
            // Iterate through the playersMap and get player names
            for (Map.Entry<String, Object> entry : playersMap.entrySet()) {
                addPlayer(entry.getKey());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Game status
        try {
            System.out.println("Message from Firebase, game state");
            Long state = (Long) data.get("state");
            if (state == 1) {
                System.out.println("State: running game");
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        ((Game) Gdx.app.getApplicationListener())
                                .setScreen(new GameView(true, docID));
                    }
                });
            } else System.out.println("State: waiting for players");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
