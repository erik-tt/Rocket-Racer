package com.rocketracer.game.Firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.rocketracer.game.FirebaseInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class FirebaseHandler implements FirebaseInterface {
    // Attributes
    FirebaseUtilities fbUtility = FirebaseUtilities.sharedInstance;
    Random random = new Random();

    private String logTag = "DocSnippets";

    // Construct

    // Getters and setters

    // Methods
    @Override
    public Integer createGame() {
        Integer pin = random.nextInt(8999) + 1000;
        Map<String, Object> gameData = new HashMap<>();
        gameData.put("pin", pin);

        // Adding game data
        fbUtility.getDb().collection("games")
                .add(gameData)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(logTag, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(logTag, "Error adding document", e);
                    }
                });

        return null;
    }

    @Override
    public void joinGame(Integer gamePin) {

    }

    @Override
    public Map<String, Integer> getHighscoreList() {
        return null;
    }

    @Override
    public void updateHighscoreList(Map<String, Integer> newScore) {

    }

    @Override
    public void writeData(HashMap<String, Object> map, String documentPath) {

    }
}
