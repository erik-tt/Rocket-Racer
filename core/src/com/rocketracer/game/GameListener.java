package com.rocketracer.game;

public interface GameListener {
    public void onGameLoaded(String docID, Integer pin);
    public void onGameError();
}
