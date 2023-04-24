package com.rocketracer.game.SharedData;
import com.badlogic.gdx.Preferences;
import com.rocketracer.game.FirebaseInterface;
import com.rocketracer.game.views.MainView;

import java.util.Map;

public class LocalData {
    // Shared Singleton instance
    /**
     * Singleton instance referencing LocalData.
     */
    public static final LocalData sharedInstance = new LocalData();

    // Attributes

    /** Private storing of highscore. */
    // Single player high scores
    private Preferences prefs;
    
    /** The firebase interface endpoints */
    private FirebaseInterface FBIHandler;
    // TODO: Player name, should update based on players input
    /** Players name. */
    public String playerName = "Player";

    // View refs
    private MainView mainView;

    // Construct - Private (Singleton)
    private LocalData() {
        
    }

    //Methods
    private int init() {
        if (prefs != null) return 0;
        try {
            // Fetch high scores from android shared preferences
            prefs = com.badlogic.gdx.Gdx.app.getPreferences("RocketRacer");
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return -1;
        }
        return 0;
    }

    // Get high score by name
    public int getHighScore(String name) {
        if (init() == -1) return -1;
        // Fetch from android shared preferences
        return prefs.getInteger(name, -1);
    }

    // Get all high scores
    /**
     * Fetches the stored 'single player' high scores.
     * @return copy of highscore map.
     */
    public Map<String, ?> getSPHighScores() {
        if (init() == -1) return null;
        // Fetch from android shared preferences
        return prefs.get();
    }

    /**
     * Sets the high score for a given name if the score is higher than the current high score.
     * @param score
     * @param name
     */
    public void setHighScore(int score, String name) {
        if (init() == -1) return;
        if (score > getHighScore(name)) {
            prefs.putInteger(name, score);
            prefs.flush();
        }
    }

    public void setMainView(MainView mainView) {
        if (this.mainView == null) this.mainView = mainView;
    }
    public MainView getMainView() {
        if (this.mainView == null)
            this.mainView = new MainView(playerName);
        return this.mainView;
    }

    // Set firebase handler
    public void setFBIHandler(FirebaseInterface FBIHandler) {
        this.FBIHandler = FBIHandler;
    }
    // Get firebase handler
    public FirebaseInterface getFBIHandler() {
        return this.FBIHandler;
    }
}
