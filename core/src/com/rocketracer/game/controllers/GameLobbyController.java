package com.rocketracer.game.controllers;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.rocketracer.game.GameEventListener;
import com.rocketracer.game.SharedData.LocalData;
import com.rocketracer.game.views.GameLobbyView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameLobbyController implements GameEventListener {
    // -- Attributes --
    private GameLobbyView lobby;

    // misc
    private List<String> playersList;

    // Game data
    private String docID;
    private Integer gamePin;

    // -- Construct --
    public GameLobbyController(GameLobbyView lobby,
                               String docID, Integer pin) {
        System.out.println("Init gamelobbycontroller");
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

    @Override
    public String getDocID() {
        return this.docID;
    }

    @Override
    public void newSnapshotData(Map<String, Object> data) {
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
    }
}
