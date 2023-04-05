package com.rocketracer.game.SharedData;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.rocketracer.game.ECS.Components.PositionComponent;

import java.util.Comparator;

/**
 * Comparator for sorting entities by their y position
 */
public class YComparator implements Comparator<Entity> {
    private ComponentMapper<PositionComponent> pMapper;

    /**
     * Constructor
     */
    public YComparator(){
        pMapper = ComponentMapper.getFor(PositionComponent.class);
    }

    @Override
    public int compare(Entity entityA, Entity entityB) {
        float ay = pMapper.get(entityA).y;
        float by = pMapper.get(entityB).y;
        int res = 0;
        if(ay > by){
            res = -1;
        } else if(ay < by){
            res = 1;
        }
        return res;
    }
}