package com.rocketracer.game.SharedData;
import com.badlogic.gdx.Preferences;
import java.util.Map;

public class LocalData {
    // Shared Singleton instance
    /**
     * Singleton instance referencing LocalData.
     */
    static final LocalData sharedInstance = new LocalData();

    // --- Attributes ---
    /** Private storing of highscore. */
    // Single player high scores
    private Preferences prefs;

    // --- Construct - Private (Singleton) ---
    private LocalData() {
        // Fetch high scores from android shared preferences
        prefs = com.badlogic.gdx.Gdx.app.getPreferences("RocketRacer");
    }

    // --- Methods ---
    // Get
    // Get high score by name
    public int getHighScore(String name) {
        // Fetch from android shared preferences
        return prefs.getInteger(name, -1);
    }

    // Get all high scores
    /**
     * Fetches the stored 'single player' high scores.
     * @return copy of highscore map.
     */
    public Map<String, ?> getSPHighScores() {
        // Fetch from android shared preferences
        return prefs.get();
    }

    // Set
    /**
     * Sets the high score for a given name if the score is higher than the current high score.
     * @param score
     * @param name
     */
    public void setHighScore(int score, String name) {
        if (score > getHighScore(name)) {
            prefs.putInteger(name, score);
            prefs.flush();
        }
    }
}
