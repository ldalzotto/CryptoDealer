package com.ldz.input;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.InputProcessor;
import com.ldz.component.CurrencyComponent;
import com.ldz.system.CurrencySystem;
import com.ldz.system.PopupSystem;

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

        System.out.println(screenX + " " + screenY);

        if (!PopupSystem.getInstance(null).popupActives()) {
            for (Entity entity :
                    CurrencySystem.getInstance().getCurrencyEntities()) {
                CurrencyComponent currencyComponent = entity.getComponent(CurrencyComponent.class);
                if (currencyComponent != null) {
                    currencyComponent.scoreToAdd = 1.0f;
                }
            }
        }
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
