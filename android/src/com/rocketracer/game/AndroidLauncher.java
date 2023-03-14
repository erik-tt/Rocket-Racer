package com.rocketracer.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.rocketracer.game.Firebase.FirebaseHandler;

public class AndroidLauncher extends AndroidApplication {
	private FirebaseHandler FBIHandler = new FirebaseHandler();
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new RocketRacer(FBIHandler), config);
	}
}
