package com.rocketracer.game;

import java.util.HashMap;

public interface FirebaseInterface {

    public void loadData();

    public void writeData(HashMap<String, Object> map, String documentPath);

}
