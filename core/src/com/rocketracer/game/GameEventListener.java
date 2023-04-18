package com.rocketracer.game;

import java.util.Map;

public interface GameEventListener {

    public String getDocID();
    public void newSnapshotData(Map<String, Object> data);

}
