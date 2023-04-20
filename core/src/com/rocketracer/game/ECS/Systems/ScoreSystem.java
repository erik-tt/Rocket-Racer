package com.rocketracer.game.ECS.Systems;



import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.rocketracer.game.ECS.Components.ScoreComponent;
import com.rocketracer.game.SharedData.GameConfig;

    public class ScoreSystem extends IteratingSystem {
        private ComponentMapper<ScoreComponent> sMapper;

        public ScoreSystem() {
            super(Family.all(ScoreComponent.class).get());
            sMapper = ComponentMapper.getFor(ScoreComponent.class);

        }

        @Override
        protected void processEntity(Entity entity, float deltaTime) {
            ScoreComponent score = sMapper.get(entity);

            //Used to change the difficulty so it progresses.
            if (score.getScore() < 2000) {
                GameConfig.DIFFICULTY = 1;
            }
            if (score.getScore() > 2000 && score.getScore() < 3000) {
                GameConfig.DIFFICULTY = 2;
            }
            if (score.getScore() > 3000 && score.getScore() < 5000) {
                GameConfig.DIFFICULTY = 3;
            }
            if (score.getScore() > 5000) {
                GameConfig.DIFFICULTY = 4;
            }

            //This needs to be checked later that works
            if(!score.gameOver){
                score.score += Math.round(Gdx.graphics.getDeltaTime() *30);
            }
        }

    }


