package com.rocketracer.game;
import com.google.firebase.firestore.FirebaseFirestore;


public class FirebaseHandler implements FirebaseInterface {


    public FirebaseHandler() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
    }
}
