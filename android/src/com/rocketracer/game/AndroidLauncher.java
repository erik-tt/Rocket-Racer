package com.rocketracer.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.firebase.FirebaseApp;
import com.rocketracer.game.Firebase.FirebaseHandler;
import com.rocketracer.game.views.RocketRacer;


public class AndroidLauncher extends AndroidApplication {
	private FirebaseHandler FBIHandler = new FirebaseHandler();
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		FirebaseApp.initializeApp(this);
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new RocketRacer(FBIHandler), config);
	}
}
