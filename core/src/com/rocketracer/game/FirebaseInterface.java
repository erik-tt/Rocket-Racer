package com.rocketracer.game;

import java.util.HashMap;
import java.util.Map;

public interface FirebaseInterface {

    // Games
    /**
     * Creates game in firebase
     * @return game pin
     */
    public Integer createGame();

    /**
     * Attempts to join a game with a game pin.
     * @param gamePin
     */
    public void joinGame(Integer gamePin, String name);

    // Highscore
    /**
     * Fetches and updates the highscore-list from firebase
     */
    public void loadHighScoreList();

    /**
     * Sets the score of the current game.
     * @param score
     */
    public void setGameScore(int score);

    /**
     * Updates the highscore list in firebase.
     * @param name
     * @param score
     */
    public void updateHighscoreList(String name, int score);

    // General
    /**
     * Writes data to a document in firebase.
     * @param map
     * @param documentPath
     */
    public void writeData(HashMap<String, Object> map, String documentPath);
    /**
     * Sets the listener for the firebase handler.
     * @param listener
     */
    public void setListener(GameListener listener);

}
