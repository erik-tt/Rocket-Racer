package com.rocketracer.game;

import java.util.HashMap;
import java.util.Map;

public class FBHandlerForTest implements FirebaseInterface{
    @Override
    public Integer createGame() {
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
