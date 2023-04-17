package com.rocketracer.game;

public interface GameJoinListener {
    public void onGameLoaded(String docID, Integer pin);
    public void onGameError();
}
