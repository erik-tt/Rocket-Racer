package com.rocketracer.game.ECS.Components;

import com.badlogic.ashley.core.Component;


public class CleanupComponent implements Component {
    /**
     * This is only used as a marker to show what entities should
     * be removed from the engine by the cleanup system. It is more
     * efficient than using an Enum, because it does not need to iterate through
     * other entities than the ones that are going to be scheduled for cleaning.
     */
}
