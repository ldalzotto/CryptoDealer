package com.ldz.input;

import com.badlogic.gdx.InputProcessor;
import com.ldz.system.CurrencySystem;
import com.ldz.system.inter.IAddScore;

/**
 * Created by Loic on 19/08/2017.
 */
public class CurrencyInputProcessor implements InputProcessor {

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        ((IAddScore)CurrencySystem.getInstance()).addScore(1.0f);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
