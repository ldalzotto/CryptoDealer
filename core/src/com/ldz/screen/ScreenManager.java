package com.ldz.screen;

import com.badlogic.gdx.Screen;

/**
 * Created by Loic on 06/08/2017.
 */
public class ScreenManager {

    private static ScreenManager _instance = null;

    public enum ScreenType {
        MAIN_GAME_SCREEN;
    }

    private Screen _currentScreenDisplayed;

    private ScreenManager() {
    }

    public static ScreenManager getInstance() {
        if (_instance == null) {
            _instance = new ScreenManager();
        }
        return _instance;
    }

    public void setScreen(ScreenType screenType) {
        switch (screenType) {
            case MAIN_GAME_SCREEN:
                _currentScreenDisplayed = getScreen(screenType);
                break;
            default:
                _currentScreenDisplayed = null;
                break;
        }
    }

    public Screen getScreen(ScreenType screenType) {
        switch (screenType) {
            case MAIN_GAME_SCREEN:
                return MainGameScreen.getInstance();
            default:
                return null;
        }
    }

    public void dispose() {
        MainGameScreen.getInstance().dispose();
    }

    public Screen get_currentScreenDisplayed() {
        return _currentScreenDisplayed;
    }

}
