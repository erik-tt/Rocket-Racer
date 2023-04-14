package com.rocketracer.game.SharedData;
import java.util.HashMap;
import java.util.Map;

public class HighScoreList {
    // Shared Singleton instance
    /**
     * Singleton instance referencing high-score list.
     */
    public static final HighScoreList sharedInstance = new HighScoreList();

    // --- Attributes ---
    /** Private storing of highscore. */
    // Single player high scores
    private Map<String, Integer> spHighScores = new HashMap<>();
    private Map<Integer, Object> mpHighScores = new HashMap<>();

    // --- Construct - Private (Singleton) ---
    private HighScoreList() {
        // Fetch all highscores from LocalData
        // Single player
        reloadHighScores();
    }

    // --- Methods ---
    private void reloadHighScores() {
        // Fetch all highscores from LocalData
        // Single player
        // Clear map
        spHighScores.clear();
        for (Map.Entry<String, ?> entry : LocalData.sharedInstance.getSPHighScores().entrySet()) {
            // Check if value is an integer and add to map
            try {
                spHighScores.put(entry.getKey(), (Integer) entry.getValue());
            } catch (ClassCastException e) {
                System.out.println("Error: HighScoreList.reloadHighScores() - " + e.getMessage());
            }
        }

        LocalData.sharedInstance.getFBIHandler().loadHighScoreList();
    }
    public void printSPHighScoreList() {
        System.out.println("HighScoreList:");
        for (Map.Entry<String, Integer> entry : spHighScores.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    // Get
    /**
     * Fetches the stored 'single player' high scores.
     * @return copy of highscore map.
     */
    public Map<String, Integer> getSPHighScores() { return new HashMap<>(spHighScores); }

    // Set
    public void addSPScore(String name, Integer score) {
        // Check if score exists and is higher than current score
        if (spHighScores.containsKey(name)) {
            if (spHighScores.get(name) < score) {
                spHighScores.put(name, score);
                LocalData.sharedInstance.setHighScore(score, name);
            }
        } else {
            spHighScores.put(name, score);
            LocalData.sharedInstance.setHighScore(score, name);
        }
    }


    // -- Multiplayer --
    public void setMPHighScores(Map<Integer, Object> highScores) {
        this.mpHighScores = highScores;
        printMPHighScoreList();
    }
    public Map<Integer, Object> getMPHighScores() { return this.mpHighScores; }
    public void printMPHighScoreList() {
        System.out.println("High-score List:");
        for (Map.Entry<Integer, Object> entry : mpHighScores.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
