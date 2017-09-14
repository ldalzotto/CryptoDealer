package com.ldz.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ldz.CryptoDealer;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 720;
        config.height = 960;
        config.resizable = false;
        new LwjglApplication(new CryptoDealer(), config);
    }
}
