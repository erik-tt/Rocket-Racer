package com.rocketracer.game.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.DocumentType;

/**
 * FirebaseHandler is a shared singleton instance class that references all the static properties
 * needed in the Firebase Firestore database handling.
 * See docs: shorturl.at/qEPS6 for firebase methods in use.
 */
public class FirebaseUtilities {
    // Attributes
    static final FirebaseUtilities sharedInstance = new FirebaseUtilities();

    private FirebaseFirestore db;

    // Construct
    private FirebaseUtilities() {
        db = FirebaseFirestore.getInstance();
    }

    // Getters and setters
    public FirebaseFirestore getDb() { return this.db; }
}
