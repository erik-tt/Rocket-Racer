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
    public void joinGame(Integer gamePin);

    // Highscore
    /**
     * Fetches the highscore list from firebase.
     * @return a map with the ten best highscores.
     */
    public Map<String, Integer> getHighscoreList();

    public void updateHighscoreList(Map<String, Integer> newScore);

    // General
    public void writeData(HashMap<String, Object> map, String documentPath);

}
