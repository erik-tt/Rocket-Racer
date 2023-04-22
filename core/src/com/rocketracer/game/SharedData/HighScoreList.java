package com.rocketracer.game.SharedData;
import com.rocketracer.game.HighScoreListener;

import java.util.HashMap;
import java.util.Map;

public class HighScoreList {
    // Shared Singleton instance
    /**
     * Singleton instance referencing high-score list.
     */
    public static final HighScoreList sharedInstance = new HighScoreList();

    //Attributes

    /** Private storing of highscore. */
    // Single player high scores
    private Map<String, Integer> spHighScores = new HashMap<>();
    private Map<Integer, Map.Entry<String, Integer>> mpHighScores = new HashMap<>();

    private HighScoreListener mpHSListener;

    // Constructor - Private (Singleton)
    private HighScoreList() {
        // Fetch all high scores from LocalData
        reloadHighScores();
    }

    //Methods
    public void reloadHighScores() {
        // Fetch all high scores from LocalData
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


    // Multiplayer
    public void setMPHighScores(Map<Integer, Map.Entry<String, Integer>> highScores) {
        this.mpHighScores = highScores;
    }
    public void listenMPHighScores(HighScoreListener highScoreListener) {
        this.mpHSListener = highScoreListener;
        reloadHighScores();
    }
    public void sendToListener() {
        if (this.mpHSListener != null)
            this.mpHSListener.onHighScoreFetched(this.mpHighScores);
        this.mpHSListener = null;
    }

    public void printMPHighScoreList() {
        System.out.println("High-score List:");
        for (Map.Entry<Integer, Map.Entry<String, Integer>> entry : mpHighScores.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
