package com.rocketracer.game.Firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.rocketracer.game.FirebaseInterface;
import com.rocketracer.game.GameListener;
import com.rocketracer.game.SharedData.HighScoreList;
import com.rocketracer.game.SharedData.LocalData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/** The Android firebase handler instance */
public class FirebaseHandler implements FirebaseInterface {
    // Attributes
    FirebaseUtilities fbUtility = FirebaseUtilities.sharedInstance;
    Random random = new Random();
    private GameListener gameListener;

    private String logTag = "DocSnippets";
    private DocumentReference currentGameRef;

    // Methods
    @Override
    public Integer createGame() {
        Integer pin = random.nextInt(89999) + 10000;
        Map<String, Object> gameData = new HashMap<>();
        gameData.put(FIRKeys.pinKey.toString(), pin);
        // -1 = waiting for players, 0 = game in progress, 1 = game finished
        gameData.put(FIRKeys.stateKey.toString(), -1);
        currentGameRef = null;

        // Adding game data
        fbUtility.getDb().collection(FIRKeys.gamesKey.toString())
                .add(gameData)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        currentGameRef = documentReference;
                        // Notify listener
                        if (gameListener != null) gameListener.onGameLoaded(documentReference.getId(), pin);
                        Log.d(logTag, "DocumentSnapshot added with ID: " + documentReference.getId());
                        updatePlayerData(documentReference.getPath(), -1);
                        updateHighscoreList(LocalData.sharedInstance.playerName, 0);
                        loadHighScoreList();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (gameListener != null) gameListener.onGameError();
                        Log.w(logTag, "Error adding document", e);
                    }
                });

        return pin;
    }

    @Override
    public void joinGame(Integer gamePin, String name) {
        currentGameRef = null;

        fbUtility.getDb().collection(FIRKeys.gamesKey.toString())
                .whereEqualTo(FIRKeys.pinKey.toString(), gamePin)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        if (task.getResult().size() == 0) {
                            if (gameListener != null) gameListener.onGameError();
                        } else {
                            String id = task.getResult().getDocuments().get(0).getId();
                            currentGameRef = task.getResult().getDocuments().get(0).getReference();
                            updatePlayerData(currentGameRef.getPath(), -1);

                            if (gameListener != null) gameListener.onGameLoaded(id, gamePin);
                        }
                    } else if (gameListener != null) gameListener.onGameError();
                });
    }

    private void updatePlayerData(String documentPath, int score) {
        // Putting player data into game
        HashMap<String, Object> data = new HashMap<>();
        Map<String, Integer> playerData = new HashMap<>();
        playerData.put(LocalData.sharedInstance.playerName, -1);
        data.put(FIRKeys.playersKey.toString(), playerData);
        writeData(data, documentPath);
    }
    @Override
    public void setGameScore(int score) {
        if (currentGameRef == null) return;
        updatePlayerData(currentGameRef.getPath(), score);
    }

    @Override
    public void loadHighScoreList() {
        // Fetch highscore list
        Map<Integer, Object> highscoreList = new HashMap<>();
        fbUtility.getDb().collection(FIRKeys.highScoresKey.toString())
            .orderBy(FIRKeys.scoreKey.toString(), Query.Direction.DESCENDING).limit(10)
            .get()
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    int place = 1;
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Map<String, Integer> scoreMap = new HashMap<>();

                        scoreMap.put(document.getString(FIRKeys.nameKey.toString()), document.getLong(FIRKeys.scoreKey.toString()).intValue());
                        highscoreList.put(place, scoreMap);
                        place += 1;
                    }
                    HighScoreList.sharedInstance.setMPHighScores(highscoreList);
                }
            });
    }

    @Override
    public void updateHighscoreList(String name, int score) {
        if (currentGameRef == null) return;
        setGameScore(score);
        // Put player score into highscore list
        Map<String, Object> gameData = new HashMap<>();
        gameData.put(FIRKeys.nameKey.toString(), name);
        gameData.put(FIRKeys.scoreKey.toString(), score);
        fbUtility.getDb().collection(FIRKeys.highScoresKey.toString())
                .add(gameData);
        currentGameRef = null;
    }

    @Override
    public void writeData(HashMap<String, Object> map, String documentPath) {
        fbUtility.getDb().document(documentPath)
                .update(map);
    }
    @Override
    public void setListener(GameListener listener) {
        this.gameListener = listener;
    }
}

// Firebase keys
enum FIRKeys {
    gamesKey("games"),
    playersKey("players"),
    pinKey("pin"),
    stateKey("state"),
    highScoresKey("highScores"),
    scoreKey("score"),
    nameKey("name");

    private final String text;

    /**
     * @param text
     */
    FIRKeys(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
