package com.rocketracer.game.ECS.Components;

import com.badlogic.ashley.core.Component;

public class ScoreComponent implements Component{

        // --- Attributes ---
        public int score;
        public boolean gameOver;

        // --- Constructor ---
        public ScoreComponent(int score) {

                this.score = score;
                this.gameOver = false;
        }



}
