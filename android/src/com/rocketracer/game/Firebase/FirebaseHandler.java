package com.rocketracer.game.Firebase;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * FirebaseHandler is a shared singleton instance class that references all the static properties
 * needed in the Firebase Firestore database handling.
 */
public class FirebaseHandler {
    // Attributes
    static final FirebaseHandler sharedInstance = new FirebaseHandler();

    private FirebaseFirestore db;

    // Construct
    private FirebaseHandler() {
        db = FirebaseFirestore.getInstance();
    }

    // Getters and setters
    public FirebaseFirestore getDb() { return this.db; }
}
