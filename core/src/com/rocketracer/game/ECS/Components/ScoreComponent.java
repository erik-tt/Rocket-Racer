package com.rocketracer.game.ECS.Components;

import com.badlogic.ashley.core.Component;

public class ScoreComponent implements Component{

        // --- Attributes ---
        public float score;

        // --- Constructor ---
        public ScoreComponent(float score) {
            this.score = score;
        }

        public float getScore(){
                return score;
        }


}
