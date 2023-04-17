package com.rocketracer.game.ECS.Systems;


import static com.badlogic.gdx.math.MathUtils.random;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.rocketracer.game.ECS.Components.FuelComponent;
import com.rocketracer.game.ECS.Components.ScoreComponent;
import com.rocketracer.game.ECS.Components.TypeComponent;
import com.rocketracer.game.views.GameOverView;
import com.rocketracer.game.views.GameView;

    public class ScoreSystem extends IteratingSystem {
        private ComponentMapper<ScoreComponent> sMapper;





        public ScoreSystem() {
            super(Family.all(ScoreComponent.class).get());
            sMapper = ComponentMapper.getFor(ScoreComponent.class);

        }

        @Override
        protected void processEntity(Entity entity, float deltaTime) {
            ScoreComponent score = sMapper.get(entity);

            //This needs to be checked later that works

            if(!score.gameOver){
                score.score += Math.round(Gdx.graphics.getDeltaTime() *30);
            }
        }

    }


