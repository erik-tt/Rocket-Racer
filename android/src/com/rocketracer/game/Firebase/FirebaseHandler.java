package com.rocketracer.game.Firebase;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.rocketracer.game.FirebaseInterface;
import com.rocketracer.game.GameEventListener;
import com.rocketracer.game.GameJoinListener;
import com.rocketracer.game.SharedData.HighScoreList;
import com.rocketracer.game.SharedData.LocalData;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/** The Android firebase handler instance */
public class FirebaseHandler implements FirebaseInterface {
    // Attributes
    FirebaseUtilities fbUtility = FirebaseUtilities.sharedInstance;
    Random random = new Random();
    private GameJoinListener gameJoinListener;

    private String logTag = "DocSnippets";
    private DocumentReference currentGameRef;
    private GameEventListener gameListener;
    private ListenerRegistration registration;

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
                        if (gameJoinListener != null) gameJoinListener.onGameLoaded(documentReference.getId(), pin);
                        Log.d(logTag, "DocumentSnapshot added with ID: " + documentReference.getId());
                        updatePlayerData(documentReference.getPath(), -1);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (gameJoinListener != null) gameJoinListener.onGameError("Error creating game. Pleas check your connection and try again.");
                        Log.w(logTag, "Error adding document", e);
                    }
                });

        return pin;
    }

    @Override
    public void joinGame(Integer gamePin, String name) {
        currentGameRef = null;

        fbUtility.getDb()
                .collection(FIRKeys.gamesKey.toString())
                .whereEqualTo(FIRKeys.pinKey.toString(), gamePin)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        if (task.getResult().size() == 0) {
                            if (gameJoinListener != null) gameJoinListener.onGameError("Could not find game");
                        } else {
                            String id = task.getResult().getDocuments().get(0).getId();

                            Map<String, Object> data = task.getResult().getDocuments().get(0).getData();
                            try {
                                Long state = (Long) data.get("state");
                                if (state == 1) {
                                    if (gameJoinListener != null) gameJoinListener.onGameError("Game has started.");
                                    System.out.println("Game already started...");
                                    return;
                                }
                            } catch (Exception e) {
                                if (gameJoinListener != null) gameJoinListener.onGameError("Error joining game.");
                                return;
                            }

                            try {
                                Map<String, Object> playersMap = (Map<String, Object>) data.get("players");
                                // Iterate through the playersMap and get player names
                                if (playersMap.containsKey(LocalData.sharedInstance.playerName)) {
                                    if (gameJoinListener != null) gameJoinListener.onGameError("Username taken.");
                                    return;
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }

                            currentGameRef = task.getResult().getDocuments().get(0).getReference();
                            updatePlayerData(currentGameRef.getPath(), -1);

                            if (gameJoinListener != null) gameJoinListener.onGameLoaded(id, gamePin);
                        }
                    } else if (gameJoinListener != null) gameJoinListener.onGameError("Error in matchmaking. Please check your connection and try again.");
                });
    }

    private void updatePlayerData(String documentPath, int score) {
        // Putting player data into game
        HashMap<String, Object> data = new HashMap<>();
        Map<String, Integer> playerData = new HashMap<>();
        playerData.put(LocalData.sharedInstance.playerName, -1);
        data.put(FIRKeys.playersKey.toString(), playerData);

        fbUtility.getDb().document(documentPath)
                .update("players." + LocalData.sharedInstance.playerName, score);
    }
    @Override
    public void setGameScore(int score) {
        if (currentGameRef == null) return;
        updatePlayerData(currentGameRef.getPath(), score);
    }

    @Override
    public void loadHighScoreList() {
        // Fetch highscore list
        Map<Integer, Map.Entry<String, Integer>> highscoreList = new HashMap<>();
        fbUtility.getDb().collection(FIRKeys.highScoresKey.toString())
            .orderBy(FIRKeys.scoreKey.toString(), Query.Direction.DESCENDING).limit(10)
            .get()
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    int place = 1;
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Map<String, Integer> scoreMap = new HashMap<>();

                        scoreMap.put(document.getString(FIRKeys.nameKey.toString()), document.getLong(FIRKeys.scoreKey.toString()).intValue());
                        Map.Entry<String, Integer> entry = scoreMap.entrySet().iterator().next();
                        highscoreList.put(place, entry);
                        place += 1;
                    }
                    HighScoreList.sharedInstance.setMPHighScores(highscoreList);
                    HighScoreList.sharedInstance.sendToListener();
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
    public void setListener(GameJoinListener listener) {
        this.gameJoinListener = listener;
    }

    @Override
    public void setGameListener(GameEventListener listener) {
        // Removing any previous listener if it has not already been removed
        if (listener.getDocID() == null) return;
        removeGameListener();

        // Setting listener
        this.gameListener = listener;

        final DocumentReference docRef = fbUtility.getDb()
                .collection("games")
                .document(listener.getDocID());
        this.registration = docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (gameListener == null) {
                    removeGameListener();
                    return;
                }
                if (e != null) {
                    Log.w("", "Listen failed.", e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    gameListener.newSnapshotData(snapshot.getData());
                } else {
                    Log.d("", "Current data: null");
                }
            }
        });
    }

    @Override
    public void removeGameListener() {
        if (this.registration != null)
            this.registration.remove();
        this.gameListener = null;
        this.registration = null;
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
