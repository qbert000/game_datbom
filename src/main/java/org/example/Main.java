package org.example;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;

public class Main extends GameApplication {

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(1000);
        gameSettings.setHeight(800);
        gameSettings.setTitle("Basic Game App");
        gameSettings.setVersion("0.1");
        gameSettings.setGameMenuEnabled(true);
        gameSettings.setAppIcon("brick.png");
        gameSettings.setDeveloperMenuEnabled(true);
        gameSettings.setGameMenuEnabled(true);
        gameSettings.setIntroEnabled(true);
    }

    public static void main(String[] args) {

        launch(args);
    }
}