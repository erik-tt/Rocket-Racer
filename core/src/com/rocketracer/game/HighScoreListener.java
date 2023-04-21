package com.rocketracer.game;

import java.util.Map;

public interface HighScoreListener {
    /** Highscore loaded. */
    public void onHighScoreFetched(Map<Integer, Map.Entry<String, Integer>> mpHighScores);
}
