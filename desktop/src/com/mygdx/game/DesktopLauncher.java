package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.team.ngulong.MyGdxGame;
import lib.Rms;
import lib.TCanvas;

public class DesktopLauncher {

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setIdleFPS(30);
        config.setForegroundFPS(30);
        config.setTitle("\u004E\u0067\u0169\u0020\u004C\u006F\u006E\u0067\u0020\u0054\u0072\u0061\u006E\u0068\u0020\u0042\u00E1");
        config.setWindowIcon("icon/icon128.png", "icon/icon32.png");
        config.setResizable(false);
        TCanvas.ScreenSize = Rms.LoadScreenSize();
        if (TCanvas.ScreenSize == 1) {
            config.setWindowedMode(800, 480);
        } else {
            config.setWindowedMode(1195, 800);
        }
        new Lwjgl3Application(new MyGdxGame(), config);
    }

}
