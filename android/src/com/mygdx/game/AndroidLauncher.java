package com.mygdx.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.team.ngulong.MyGdxGame;

import lib.Rms;
import lib.TCanvas;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		TCanvas.ScreenSize = Rms.LoadScreenSize();

		initialize(new MyGdxGame(), config);
	}

}
